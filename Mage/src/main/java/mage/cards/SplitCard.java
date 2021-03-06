/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards;

import mage.abilities.Abilities;
import mage.abilities.AbilitiesImpl;
import mage.abilities.Ability;
import mage.abilities.SpellAbility;
import mage.constants.CardType;
import mage.constants.SpellAbilityType;
import mage.constants.Zone;
import mage.game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LevelX2
 */
public abstract class SplitCard extends CardImpl {

    protected Card leftHalfCard;
    protected Card rightHalfCard;

    public SplitCard(UUID ownerId, CardSetInfo setInfo, CardType[] cardTypes, String costsLeft, String costsRight, SpellAbilityType spellAbilityType) {
        this(ownerId, setInfo, cardTypes, cardTypes, costsLeft, costsRight, spellAbilityType);
    }

    public SplitCard(UUID ownerId, CardSetInfo setInfo, CardType[] typesLeft, CardType[] typesRight, String costsLeft, String costsRight, SpellAbilityType spellAbilityType) {
        super(ownerId, setInfo, CardType.mergeTypes(typesLeft, typesRight), costsLeft + costsRight, spellAbilityType);
        String[] names = setInfo.getName().split(" // ");
        leftHalfCard = new SplitCardHalfImpl(this.getOwnerId(), new CardSetInfo(names[0], setInfo.getExpansionSetCode(), setInfo.getCardNumber(), setInfo.getRarity(), setInfo.getGraphicInfo()), typesLeft, costsLeft, this, SpellAbilityType.SPLIT_LEFT);
        rightHalfCard = new SplitCardHalfImpl(this.getOwnerId(), new CardSetInfo(names[1], setInfo.getExpansionSetCode(), setInfo.getCardNumber(), setInfo.getRarity(), setInfo.getGraphicInfo()), typesRight, costsRight, this, SpellAbilityType.SPLIT_RIGHT);
        this.splitCard = true;
    }

    public SplitCard(SplitCard card) {
        super(card);
        this.leftHalfCard = card.getLeftHalfCard().copy();
        ((SplitCardHalf) leftHalfCard).setParentCard(this);
        this.rightHalfCard = card.rightHalfCard.copy();
        ((SplitCardHalf) rightHalfCard).setParentCard(this);
    }

    public SplitCardHalf getLeftHalfCard() {
        return (SplitCardHalf) leftHalfCard;
    }

    public SplitCardHalf getRightHalfCard() {
        return (SplitCardHalf) rightHalfCard;
    }

    @Override
    public void assignNewId() {
        super.assignNewId();
        leftHalfCard.assignNewId();
        rightHalfCard.assignNewId();
    }

    @Override
    public void setCopy(boolean isCopy) {
        super.setCopy(isCopy);
        leftHalfCard.setCopy(isCopy);
        rightHalfCard.setCopy(isCopy);
    }

    @Override
    public boolean moveToZone(Zone toZone, UUID sourceId, Game game, boolean flag, List<UUID> appliedEffects) {
        if (super.moveToZone(toZone, sourceId, game, flag, appliedEffects)) {
            game.getState().setZone(getLeftHalfCard().getId(), toZone);
            game.getState().setZone(getRightHalfCard().getId(), toZone);
            return true;
        }
        return false;
    }

    @Override
    public boolean moveToExile(UUID exileId, String name, UUID sourceId, Game game, List<UUID> appliedEffects) {
        if (super.moveToExile(exileId, name, sourceId, game, appliedEffects)) {
            Zone currentZone = game.getState().getZone(getId());
            game.getState().setZone(getLeftHalfCard().getId(), currentZone);
            game.getState().setZone(getRightHalfCard().getId(), currentZone);
            return true;
        }
        return false;
    }

    @Override
    public boolean cast(Game game, Zone fromZone, SpellAbility ability, UUID controllerId) {
        switch (ability.getSpellAbilityType()) {
            case SPLIT_LEFT:
                return this.getLeftHalfCard().cast(game, fromZone, ability, controllerId);
            case SPLIT_RIGHT:
                return this.getRightHalfCard().cast(game, fromZone, ability, controllerId);
            default:
                return super.cast(game, fromZone, ability, controllerId);
        }
    }

    @Override
    public void setZone(Zone zone, Game game) {
        super.setZone(zone, game);
        game.setZone(getLeftHalfCard().getId(), zone);
        game.setZone(getRightHalfCard().getId(), zone);
    }

    @Override
    public Abilities<Ability> getAbilities() {
        Abilities<Ability> allAbilites = new AbilitiesImpl<>();
        for (Ability ability : super.getAbilities()) {
            if (ability instanceof SpellAbility
                    && ((SpellAbility) ability).getSpellAbilityType() != SpellAbilityType.SPLIT
                    && ((SpellAbility) ability).getSpellAbilityType() != SpellAbilityType.SPLIT_AFTERMATH) {
                allAbilites.add(ability);
            }
        }
        allAbilites.addAll(leftHalfCard.getAbilities());
        allAbilites.addAll(rightHalfCard.getAbilities());
        return allAbilites;
    }

    /**
     * Currently only gets the fuse SpellAbility if there is one, but generally
     * gets any abilities on a split card as a whole, and not on either half
     * individually.
     *
     * @return
     */
    public Abilities<Ability> getSharedAbilities() {
        return super.getAbilities();
    }

    @Override
    public Abilities<Ability> getAbilities(Game game) {
        Abilities<Ability> allAbilites = new AbilitiesImpl<>();
        for (Ability ability : super.getAbilities(game)) {
            if (ability instanceof SpellAbility
                    && ((SpellAbility) ability).getSpellAbilityType() != SpellAbilityType.SPLIT
                    && ((SpellAbility) ability).getSpellAbilityType() != SpellAbilityType.SPLIT_AFTERMATH) {
                allAbilites.add(ability);
            }
        }
        allAbilites.addAll(leftHalfCard.getAbilities(game));
        allAbilites.addAll(rightHalfCard.getAbilities(game));
        return allAbilites;
    }

    @Override
    public List<String> getRules() {
        List<String> rules = new ArrayList<>();
        if (getSpellAbility().getSpellAbilityType() == SpellAbilityType.SPLIT_FUSED) {
            rules.add("--------------------------------------------------------------------------\nFuse (You may cast one or both halves of this card from your hand.)");
        }
        return rules;
    }

    @Override
    public void setOwnerId(UUID ownerId) {
        super.setOwnerId(ownerId);
        abilities.setControllerId(ownerId);
        leftHalfCard.getAbilities().setControllerId(ownerId);
        leftHalfCard.setOwnerId(ownerId);
        rightHalfCard.getAbilities().setControllerId(ownerId);
        rightHalfCard.setOwnerId(ownerId);

    }

}
