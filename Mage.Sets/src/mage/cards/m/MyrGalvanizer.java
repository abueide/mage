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

package mage.cards.m;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.SubtypePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;

/**
 *
 * @author Loki
 */
public class MyrGalvanizer extends CardImpl {
    static final FilterCreaturePermanent filter = new FilterCreaturePermanent("Myr creatures");

    static {
        filter.add(new SubtypePredicate(SubType.MYR));
    }

    public MyrGalvanizer (UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT,CardType.CREATURE},"{3}");
        this.subtype.add("Myr");
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);
        // Other Myr creatures you control get +1/+1.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new BoostControlledEffect(1, 1, Duration.WhileOnBattlefield, filter, true)));
        
        // {1}, {T}: Untap each other Myr you control.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new MyrGalvanizerEffect(), new TapSourceCost());
        ability.addCost(new GenericManaCost(1));
        this.addAbility(ability);
    }

    public MyrGalvanizer (final MyrGalvanizer card) {
        super(card);
    }

    @Override
    public MyrGalvanizer copy() {
        return new MyrGalvanizer(this);
    }
}

class MyrGalvanizerEffect extends OneShotEffect {

    MyrGalvanizerEffect() {
        super(Outcome.Untap);
        staticText = "Untap each other Myr you control";
    }

    MyrGalvanizerEffect(final MyrGalvanizerEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        for (Permanent permanent: game.getBattlefield().getActivePermanents(MyrGalvanizer.filter, source.getControllerId(), game)) {
           if (!permanent.getId().equals(source.getSourceId())) {
                permanent.untap(game);
            }
        }
        return true;
    }

    @Override
    public MyrGalvanizerEffect copy() {
        return new MyrGalvanizerEffect(this);
    }
}
