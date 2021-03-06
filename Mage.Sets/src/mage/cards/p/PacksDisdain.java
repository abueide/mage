/*
 * Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.p;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.choices.Choice;
import mage.choices.ChoiceCreatureType;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.predicate.mageobject.SubtypePredicate;
import mage.game.Game;
import mage.players.Player;
import mage.target.common.TargetCreaturePermanent;
import mage.target.targetpointer.FixedTarget;

/**
 *
 * @author fenhl
 */
public class PacksDisdain extends CardImpl {

    public PacksDisdain(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{1}{B}");

        // Choose a creature type. Target creature gets -1/-1 until end of turn for each permanent of the chosen type you control.
        this.getSpellAbility().addEffect(new PacksDisdainEffect());
        this.getSpellAbility().addTarget(new TargetCreaturePermanent());
    }

    public PacksDisdain(final PacksDisdain card) {
        super(card);
    }

    @Override
    public PacksDisdain copy() {
        return new PacksDisdain(this);
    }
}

class PacksDisdainEffect extends OneShotEffect {

    PacksDisdainEffect() {
        super(Outcome.UnboostCreature);
        this.staticText = "Choose a creature type. Target creature gets -1/-1 until end of turn for each permanent of the chosen type you control";
    }

    PacksDisdainEffect(final PacksDisdainEffect effect) {
        super(effect);
    }

    @Override
    public PacksDisdainEffect copy() {
        return new PacksDisdainEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        if (player != null) {
            Choice typeChoice = new ChoiceCreatureType();
            while (!player.choose(Outcome.UnboostCreature, typeChoice, game)) {
                if (!player.canRespond()) {
                    return false;
                }
            }
            FilterControlledCreaturePermanent filter = new FilterControlledCreaturePermanent();
            filter.add(new SubtypePredicate(SubType.byDescription(typeChoice.getChoice())));
            DynamicValue negativePermanentsCount = new PermanentsOnBattlefieldCount(filter, -1);
            ContinuousEffect effect = new BoostTargetEffect(negativePermanentsCount, negativePermanentsCount, Duration.EndOfTurn, true);
            effect.setTargetPointer(new FixedTarget(source.getFirstTarget()));
            game.addEffect(effect, source);
            return true;
        }
        return false;
    }
}
