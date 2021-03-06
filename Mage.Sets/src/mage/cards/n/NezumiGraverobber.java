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
package mage.cards.n;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.FlipSourceEffect;
import mage.abilities.effects.common.ReturnFromGraveyardToBattlefieldTargetEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SuperType;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.common.FilterCreatureCard;
import mage.game.Game;
import mage.game.permanent.token.Token;
import mage.players.Player;
import mage.target.Target;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCardInOpponentsGraveyard;

/**
 * @author Loki
 */
public class NezumiGraverobber extends CardImpl {

    public NezumiGraverobber(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{B}");
        this.subtype.add("Rat");
        this.subtype.add("Rogue");

        this.power = new MageInt(2);
        this.toughness = new MageInt(1);
        this.flipCard = true;
        this.flipCardName = "Nighteyes the Desecrator";

        // {1}{B}: Exile target card from an opponent's graveyard. If no cards are in that graveyard, flip Nezumi Graverobber.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new ExileTargetEffect(), new ManaCostsImpl("{1}{B}"));
        Target target = new TargetCardInOpponentsGraveyard(new FilterCard("card from an opponent's graveyard"));
        ability.addTarget(target);
        ability.addEffect(new NezumiGraverobberFlipEffect());
        this.addAbility(ability);
    }

    public NezumiGraverobber(final NezumiGraverobber card) {
        super(card);
    }

    @Override
    public NezumiGraverobber copy() {
        return new NezumiGraverobber(this);
    }
}

class NezumiGraverobberFlipEffect extends OneShotEffect {

    NezumiGraverobberFlipEffect() {
        super(Outcome.BecomeCreature);
        staticText = "If no cards are in that graveyard, flip {this}";
    }

    NezumiGraverobberFlipEffect(final NezumiGraverobberFlipEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Card card = game.getCard(targetPointer.getFirst(game, source));
        if (card != null) {
            Player player = game.getPlayer(card.getOwnerId());
            if (player != null) {
                if (player.getGraveyard().isEmpty()) {
                    return new FlipSourceEffect(new NighteyesTheDesecratorToken()).apply(game, source);
                }
            }
        }
        return false;
    }

    @Override
    public NezumiGraverobberFlipEffect copy() {
        return new NezumiGraverobberFlipEffect(this);
    }

}

class NighteyesTheDesecratorToken extends Token {
    
    NighteyesTheDesecratorToken() {            
        super("Nighteyes the Desecrator", "");
       addSuperType(SuperType.LEGENDARY);
        cardType.add(CardType.CREATURE);
        color.setBlack(true);
        subtype.add("Rat");
        subtype.add("Wizard");
        power = new MageInt(4);
        toughness = new MageInt(2);
        // {4}{B}: Put target creature card from a graveyard onto the battlefield under your control.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new ReturnFromGraveyardToBattlefieldTargetEffect(), new ManaCostsImpl("{4}{B}"));
        ability.addTarget(new TargetCardInGraveyard(new FilterCreatureCard("creature card from a graveyard")));
        this.addAbility(ability);
    }
}
