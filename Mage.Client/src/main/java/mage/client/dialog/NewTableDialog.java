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
package mage.client.dialog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import mage.cards.decks.importer.DeckImporterUtil;
import mage.client.MageFrame;
import mage.client.components.MageComponents;
import mage.client.table.TablePlayerPanel;
import mage.client.util.Event;
import mage.client.util.Listener;
import mage.constants.MatchTimeLimit;
import mage.constants.MultiplayerAttackOption;
import mage.constants.RangeOfInfluence;
import mage.game.match.MatchOptions;
import mage.remote.Session;
import mage.view.GameTypeView;
import mage.view.TableView;
import org.apache.log4j.Logger;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public class NewTableDialog extends MageDialog {

    private static final Logger logger = Logger.getLogger(NewTableDialog.class);

    private TableView table;
    private UUID playerId;
    private UUID roomId;
    private Session session;
    private List<TablePlayerPanel> players = new ArrayList<TablePlayerPanel>();

    private static final String LIMITED = "Limited";

    /** Creates new form NewTableDialog */
    public NewTableDialog() {
        initComponents();
        player1Panel.showLevel(false);
        this.spnNumWins.setModel(new SpinnerNumberModel(1, 1, 5, 1));
        this.spnFreeMulligans.setModel(new SpinnerNumberModel(0, 0, 5, 1));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbDeckType = new javax.swing.JLabel();
        cbDeckType = new javax.swing.JComboBox();
        lbTimeLimit = new javax.swing.JLabel();
        cbTimeLimit = new javax.swing.JComboBox();
        lblGameType = new javax.swing.JLabel();
        cbGameType = new javax.swing.JComboBox();
        lblFreeMulligans = new javax.swing.JLabel();
        spnFreeMulligans = new javax.swing.JSpinner();
        lblNumPlayers = new javax.swing.JLabel();
        spnNumPlayers = new javax.swing.JSpinner();
        lblRange = new javax.swing.JLabel();
        cbRange = new javax.swing.JComboBox();
        lblAttack = new javax.swing.JLabel();
        cbAttackOption = new javax.swing.JComboBox();
        lblNumWins = new javax.swing.JLabel();
        spnNumWins = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        player1Panel = new mage.client.table.NewPlayerPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        pnlOtherPlayers = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setTitle("New Table");

        lblName.setLabelFor(txtName);
        lblName.setText("Name:");

        lbDeckType.setText("Deck Type:");

        lbTimeLimit.setText("Time Limit:");
        lbTimeLimit.setToolTipText("The activie time a player may use to finish the match. If the time runs out, the player looses the current game.");

        lblGameType.setText("Game Type:");

        cbGameType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGameTypeActionPerformed(evt);
            }
        });

        lblFreeMulligans.setText("Free Mulligans:");
        lblFreeMulligans.setToolTipText("The number of mulligans a player can use without decreasing the number of drawn cards.");

        lblNumPlayers.setLabelFor(spnNumPlayers);
        lblNumPlayers.setText("Players");

        spnNumPlayers.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                numPlayersChanged(evt);
            }
        });

        lblRange.setLabelFor(cbRange);
        lblRange.setText("Range of Influence");

        lblAttack.setLabelFor(cbAttackOption);
        lblAttack.setText("Attack Option");

        lblNumWins.setLabelFor(spnNumWins);
        lblNumWins.setText("Wins");
        lblNumWins.setToolTipText("How many games has a player to win to win the match.");

        spnNumWins.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnNumWinsnumPlayersChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Player 1 (You)");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Other Players");

        pnlOtherPlayers.setLayout(new java.awt.GridLayout(0, 1));

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName)
                            .addComponent(lbDeckType)
                            .addComponent(lblGameType))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbGameType, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblFreeMulligans)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(spnFreeMulligans, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cbDeckType, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbTimeLimit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTimeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumPlayers)
                            .addComponent(spnNumPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRange)
                            .addComponent(cbRange, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAttack)
                            .addComponent(cbAttackOption, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spnNumWins, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumWins)))
                    .addComponent(jSeparator2)
                    .addComponent(player1Panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlOtherPlayers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDeckType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTimeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDeckType)
                    .addComponent(lbTimeLimit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spnFreeMulligans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFreeMulligans))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbGameType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGameType)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblNumPlayers)
                            .addGap(0, 0, 0)
                            .addComponent(spnNumPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblRange)
                            .addGap(0, 0, 0)
                            .addComponent(cbRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblAttack)
                            .addGap(0, 0, 0)
                            .addComponent(cbAttackOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNumWins)
                        .addGap(0, 0, 0)
                        .addComponent(spnNumWins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(player1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlOtherPlayers, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOK))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(201, 201, 201)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(285, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.table = null;
        this.playerId = null;
        this.hideDialog();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        GameTypeView gameType = (GameTypeView) cbGameType.getSelectedItem();
        MatchOptions options = new MatchOptions(this.txtName.getText(), gameType.getName());
        options.getPlayerTypes().add("Human");
        for (TablePlayerPanel player: players) {
            options.getPlayerTypes().add(player.getPlayerType());
        }
        options.setDeckType((String) this.cbDeckType.getSelectedItem());
        options.setLimited(false);
        options.setMatchTimeLimit((MatchTimeLimit) this.cbTimeLimit.getSelectedItem());
        options.setAttackOption((MultiplayerAttackOption) this.cbAttackOption.getSelectedItem());
        options.setRange((RangeOfInfluence) this.cbRange.getSelectedItem());
        options.setWinsNeeded((Integer)this.spnNumWins.getValue());
        options.setFreeMulligans((Integer)this.spnFreeMulligans.getValue());
        saveGameSettingsToPrefs(options, this.player1Panel.getDeckFile());

        table = session.createTable(roomId, options);
        if (table == null) {
            JOptionPane.showMessageDialog(MageFrame.getDesktop(), "Error creating table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if (session.joinTable(roomId, table.getTableId(), this.player1Panel.getPlayerName(), "Human", 1, DeckImporterUtil.importDeck(this.player1Panel.getDeckFile()))) {
                for (TablePlayerPanel player: players) {
                    if (!player.getPlayerType().equals("Human")) {
                        if (!player.joinTable(roomId, table.getTableId())) {
                            JOptionPane.showMessageDialog(MageFrame.getDesktop(), "Error joining table.", "Error", JOptionPane.ERROR_MESSAGE);
                            session.removeTable(roomId, table.getTableId());
                            table = null;
                            return;
                        }
                    }
                }
                this.hideDialog();
                return;
            }
        } catch (FileNotFoundException ex) {
            handleError(ex);
        } catch (IOException ex) {
            handleError(ex);
        } catch (ClassNotFoundException ex) {
            handleError(ex);
        }
        // JOptionPane.showMessageDialog(MageFrame.getDesktop(), "Error joining table.", "Error", JOptionPane.ERROR_MESSAGE);
        session.removeTable(roomId, table.getTableId());
        table = null;
    }//GEN-LAST:event_btnOKActionPerformed

    private void cbGameTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGameTypeActionPerformed
        setGameOptions();
    }//GEN-LAST:event_cbGameTypeActionPerformed

    private void numPlayersChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_numPlayersChanged
        int numPlayers = (Integer)this.spnNumPlayers.getValue() - 1;
        createPlayers(numPlayers);
    }//GEN-LAST:event_numPlayersChanged

    private void spnNumWinsnumPlayersChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnNumWinsnumPlayersChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_spnNumWinsnumPlayersChanged

    private void setGameOptions() {
        GameTypeView gameType = (GameTypeView) cbGameType.getSelectedItem();
        this.spnNumPlayers.setModel(new SpinnerNumberModel(gameType.getMinPlayers(), gameType.getMinPlayers(), gameType.getMaxPlayers(), 1));
        this.spnNumPlayers.setEnabled(gameType.getMinPlayers() != gameType.getMaxPlayers());
        this.cbAttackOption.setEnabled(gameType.isUseAttackOption());
        this.cbRange.setEnabled(gameType.isUseRange());
        createPlayers(gameType.getMinPlayers() - 1);
    }

    private void createPlayers(int numPlayers) {
        if (numPlayers > players.size()) {
            while (players.size() != numPlayers) {
                TablePlayerPanel playerPanel = new TablePlayerPanel();
                playerPanel.init(players.size() + 2);
                players.add(playerPanel);
                playerPanel.addPlayerTypeEventListener(
                    new Listener<Event> () {
                        @Override
                        public void event(Event event) {
                            drawPlayers();
                        }
                    }
                );
            }
        }
        else if (numPlayers < players.size()) {
            while (players.size() != numPlayers) {
                players.remove(players.size() - 1);
            }
        }
        drawPlayers();
    }

    private void drawPlayers() {
        this.pnlOtherPlayers.removeAll();
        for (TablePlayerPanel panel: players) {
            this.pnlOtherPlayers.add(panel);
        }
        this.pack();
        this.revalidate();
        this.repaint();
    }

    private void handleError(Exception ex) {
        logger.fatal("Error loading deck", ex);
        //JOptionPane.showMessageDialog(MageFrame.getDesktop(), "Error loading deck.", "Error", JOptionPane.ERROR_MESSAGE);
        MageFrame.getInstance().showErrorDialog("Error loading deck.", ex.getMessage());
    }

    public void showDialog(UUID roomId) {
        session = MageFrame.getSession();
        MageFrame.getUI().addButton(MageComponents.NEW_TABLE_OK_BUTTON, btnOK);
        this.player1Panel.setPlayerName(session.getUserName());
        cbGameType.setModel(new DefaultComboBoxModel(session.getGameTypes().toArray()));
        cbDeckType.setModel(new DefaultComboBoxModel(session.getDeckTypes()));
        selectLimitedByDefault();
        cbTimeLimit.setModel(new DefaultComboBoxModel(MatchTimeLimit.values()));
        cbRange.setModel(new DefaultComboBoxModel(RangeOfInfluence.values()));
        cbAttackOption.setModel(new DefaultComboBoxModel(MultiplayerAttackOption.values()));

        setGameSettingsFromPrefs();

        this.roomId = roomId;
        this.setModal(true);
        setGameOptions();
        this.setLocation(150, 100);
        this.setVisible(true);
    }

    public TableView getTable() {
        return table;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    private void selectLimitedByDefault() {
        for (int i = 0; i < cbDeckType.getItemCount(); i++) {
            String name = (String)cbDeckType.getItemAt(i);
            if (name.equals(LIMITED)) {
                cbDeckType.setSelectedIndex(i);
                break;
            }
        }
    }

    /**
     * set the table settings from java prefs 
     */
    private void setGameSettingsFromPrefs () {
        txtName.setText(PreferencesDialog.getCachedValue(PreferencesDialog.KEY_NEW_TABLE_NAME, "Game"));
        String gameTypeName = PreferencesDialog.getCachedValue(PreferencesDialog.KEY_NEW_TABLE_GAME_TYPE, "Two Player Duel");
        for (GameTypeView gtv : session.getGameTypes()) {
            if (gtv.getName().equals(gameTypeName)) {
                cbGameType.setSelectedItem(gtv);
                break;
            }
        }
        int timeLimit = Integer.parseInt(PreferencesDialog.getCachedValue(PreferencesDialog.KEY_NEW_TABLE_TIME_LIMIT, "1500"));
        for (MatchTimeLimit mtl :MatchTimeLimit.values()) {
            if (mtl.getTimeLimit() == timeLimit) {
                this.cbTimeLimit.setSelectedItem(mtl);
                break;
            }
        }
        cbDeckType.setSelectedItem(PreferencesDialog.getCachedValue(PreferencesDialog.KEY_NEW_TABLE_DECK_TYPE, "Limited"));
        String deckFile = PreferencesDialog.getCachedValue(PreferencesDialog.KEY_NEW_TABLE_DECK_FILE, null);
        if (deckFile != null) {
            this.player1Panel.setDeckFile(deckFile);
        }
        this.spnNumWins.setValue(Integer.parseInt(PreferencesDialog.getCachedValue(PreferencesDialog.KEY_NEW_TABLE_NUMBER_OF_WINS, "2")));
        this.spnNumPlayers.setValue(Integer.parseInt(PreferencesDialog.getCachedValue(PreferencesDialog.KEY_NEW_TABLE_NUMBER_PLAYERS, "2")));
        int range = Integer.parseInt(PreferencesDialog.getCachedValue(PreferencesDialog.KEY_NEW_TABLE_RANGE, "1"));
        for (RangeOfInfluence roi :RangeOfInfluence.values()) {
            if (roi.getRange() == range) {
                this.cbRange.setSelectedItem(roi);
                break;
            }
        }
        String attackOption = PreferencesDialog.getCachedValue(PreferencesDialog.KEY_NEW_TABLE_ATTACK_OPTION, "Attack Multiple Players");
        for (MultiplayerAttackOption mao :MultiplayerAttackOption.values()) {
            if (mao.toString().equals(attackOption)) {
                this.cbAttackOption.setSelectedItem(mao);
                break;
            }
        }
    }

    /**
     * Save the settings to java prefs to reload it next time the dialog will be created
     *
     * @param options
     * @param deckFile
     */
    private void saveGameSettingsToPrefs(MatchOptions options, String deckFile) {
        PreferencesDialog.saveValue(PreferencesDialog.KEY_NEW_TABLE_NAME, options.getName());
        PreferencesDialog.saveValue(PreferencesDialog.KEY_NEW_TABLE_DECK_TYPE, options.getDeckType());
        PreferencesDialog.saveValue(PreferencesDialog.KEY_NEW_TABLE_TIME_LIMIT, Integer.toString(options.getPriorityTime()));
        PreferencesDialog.saveValue(PreferencesDialog.KEY_NEW_TABLE_GAME_TYPE, options.getGameType());
        PreferencesDialog.saveValue(PreferencesDialog.KEY_NEW_TABLE_NUMBER_OF_WINS, Integer.toString(options.getWinsNeeded()));
        PreferencesDialog.saveValue(PreferencesDialog.KEY_NEW_TABLE_DECK_FILE, deckFile);
        PreferencesDialog.saveValue(PreferencesDialog.KEY_NEW_TABLE_NUMBER_PLAYERS, spnNumPlayers.getValue().toString());
        PreferencesDialog.saveValue(PreferencesDialog.KEY_NEW_TABLE_RANGE, Integer.toString(options.getRange().getRange()));
        PreferencesDialog.saveValue(PreferencesDialog.KEY_NEW_TABLE_ATTACK_OPTION, options.getAttackOption().toString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox cbAttackOption;
    private javax.swing.JComboBox cbDeckType;
    private javax.swing.JComboBox cbGameType;
    private javax.swing.JComboBox cbRange;
    private javax.swing.JComboBox cbTimeLimit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbDeckType;
    private javax.swing.JLabel lbTimeLimit;
    private javax.swing.JLabel lblAttack;
    private javax.swing.JLabel lblFreeMulligans;
    private javax.swing.JLabel lblGameType;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumPlayers;
    private javax.swing.JLabel lblNumWins;
    private javax.swing.JLabel lblRange;
    private mage.client.table.NewPlayerPanel player1Panel;
    private javax.swing.JPanel pnlOtherPlayers;
    private javax.swing.JSpinner spnFreeMulligans;
    private javax.swing.JSpinner spnNumPlayers;
    private javax.swing.JSpinner spnNumWins;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

}
