package Application;

import Entities.Artist;
import Entities.DB;
import Entities.Listener;
import Entities.Playlist;
import Entities.Song;
import Manager.ArtistManager;
import Manager.ListenerManager;
import Manager.PlaylistManager;
import Manager.SongManager;
import Manager.UserManager;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Application;

/**
 *
 * @author cha09nts001.1
 */
public class JGUIFrame extends javax.swing.JFrame implements KeyListener
{
    //bringing the dbmanager into this class to connect java and sql
    private DB db = new DB();
    //counter to keep track of objects
    private int count = 0;
    //importing manager classes to display each of their tables
    private ArtistManager artM = new ArtistManager();
    private ListenerManager listM = new ListenerManager();
    private PlaylistManager playM = new PlaylistManager();
    private SongManager songM = new SongManager();
    private UserManager userM = new UserManager();
    //boolean variable for whether or not the table will be refreshed
    private boolean refresh = false;
    
    //default models for my tables in the gui
    private DefaultTableModel modelArtist = null;
    private DefaultTableModel modelListener = null;
    private DefaultTableModel modelPlaylists = null;
    private DefaultTableModel modelSong = null;
    
    public JGUIFrame() 
    {
        initComponents();
        
        //user default table model to take data from sql and display it from the contents in the java table
        modelArtist = (DefaultTableModel)tblArtists.getModel();
        modelListener = (DefaultTableModel)tblListeners.getModel();
        modelPlaylists = (DefaultTableModel)tblPlaylists.getModel();
        modelSong = (DefaultTableModel)tblSongs.getModel();
        
        //refreshing and loading all data in different tables to be viewed
        clearArtistModel();
        loadArtists();
        
        clearListenersModel();
        loadListeners();
        
        clearPlaylistsModel();
        loadPlaylist();
        
        clearSongModel();
        loadSongs();
    }
    
    //method to clear/delete all data in artist tbl
    public void clearArtistModel()
    {
        //refreshing data in artist table
        refresh = true;
        //loop to recieve all data
        while(modelArtist.getRowCount()>0)
        {
            modelArtist.removeRow(0);
        }
        //when loop is done refreshing will stop 
        refresh = false;
    }
    
    //method to clear/delete all data in listener tbl
    public void clearListenersModel()
    {
        //refreshing data in listeners table
        refresh = true;
        //using while loop to recieve all data
        while(modelListener.getRowCount()>0)
        {
            modelListener.removeRow(0);
        }
        //refreshing will stop when loop ends
        refresh = false;
    }
    
    //method to clear/delete all data in playlist table
    public void clearPlaylistsModel()
    {
        //refreshing data in playlist table
        refresh = true;
        //using while loop to recieve all data
        while(modelPlaylists.getRowCount()>0)
        {
            modelPlaylists.removeRow(0);
        }
        //refreshing will stop when loop ends
        refresh = false;
    }
    
    //method to clear/delete all data in song tbl
    public void clearSongModel()
    {
        //refreshing data in song table
        refresh = true;
        //using while loop to recieve all data
        while(modelSong.getRowCount()>0)
        {
            modelSong.removeRow(0);
        }
        //refreshing will stop when loop is done
        refresh = false;
    }
    
    //creating method to load all artist data into tbl
    public void loadArtists()
    {
        //refreshing/loading table with new data
        refresh = true;
        Artist[] artArr = artM.getArtistArr();
        //creating for loop for every single piece of data that needs to be added into the table
        for(int i = 0; i < artM.getSizeL(); i++)
        {
            modelArtist.addRow(new Object[] {artArr[i].getArtist(), artArr[i].isGrammyNominated(), artArr[i].getLatestAlbum(), artArr[i].getGenre(), artArr[i].getAlbumSales(), artArr[i].getUpcomingTours(), artArr[i].getPhoneNum(), artArr[i].getWebsite(), artArr[i].getWebsite(), artArr[i].getStreamingPlatform(), artArr[i].getMostStreamedSong(), artArr[i].getOtherOccupation()});
        }
        
        //adding rows to table
        modelArtist.addRow(new Object[] {});
        
        //stopping table from refreshing
        refresh = false;
    }
    
    //method to load data into listeners table
    public void loadListeners()
    {
        //refreshing/loading table with new data
        refresh = true;
        Listener[] listArr = listM.getListenerArr();
        //creating for loop for every single piece of data that needs to be added into the table
        for(int i = 0; i<listM.getSizeL(); i++)
        {
            modelListener.addRow(new Object[] {listArr[i].getListener(), listArr[i].getName(), listArr[i].getSurname(), listArr[i].getPrefferedGenre(), listArr[i].getUsername()});
        }
        
        //adding row to table
        modelListener.addRow(new Object[] {});
        
        //stopping table from refreshing
        refresh = false;
    }
    
    public void loadPlaylist()
    {
        //refreshing/loading table with new data
        refresh = true;
        Playlist[] playArr = playM.getPlaylistArr();
        //creating for loop for every single piece of data that needs to be added into the table
        for(int i = 0; i<playM.getSizePl(); i++)
        {
            modelPlaylists.addRow(new Object[] {playArr[i].getListenerID(), playArr[i].getSongID()});
        }
        
        //adding row to table
        modelPlaylists.addRow(new Object[] {});
        
        //stopping table from refreshing
        refresh = false;
    }
    
    public void loadSongs()
    {
        //refreshing/loading table with new data
        refresh = true;
        Song[] songArr = songM.getSongArr();
        //creating for loop for every single piece of data that needs to be added into the table
        for(int i = 0; i<songM.getSizeS(); i++)
        {
            modelSong.addRow(new Object[] {songArr[i].getArtistID(), songArr[i].getSongName(), songArr[i].getReleaseDate(), songArr[i].getGenre(), songArr[i].getDuration(), songArr[i].getAlbum()});
        }
        
        //adding row to table
        modelListener.addRow(new Object[] {});
        
        //stopping table from refreshing
        refresh = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPnlLogin = new javax.swing.JPanel();
        lblLogoLogin = new javax.swing.JLabel();
        txtFldUsername = new javax.swing.JTextField();
        lblUsernameLogin = new javax.swing.JLabel();
        lblPasswordLogin = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        lblInstructionAdd = new javax.swing.JLabel();
        lblHelp = new javax.swing.JLabel();
        lblErrorMessage = new javax.swing.JLabel();
        txtFldPassword = new javax.swing.JPasswordField();
        JPnlAddUser = new javax.swing.JPanel();
        lblLogoAddUser = new javax.swing.JLabel();
        lblAddUsername = new javax.swing.JLabel();
        txtFldAddUser = new javax.swing.JTextField();
        lblAddPassword = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblAddUserSuc = new javax.swing.JLabel();
        lblBackToLogin = new javax.swing.JLabel();
        txtFldAddPassword = new javax.swing.JPasswordField();
        JPnlMainMenu = new javax.swing.JPanel();
        lblLogoMenu = new javax.swing.JLabel();
        btnArtists = new javax.swing.JButton();
        btnPlaylists = new javax.swing.JButton();
        btnListeners = new javax.swing.JButton();
        btnSongs = new javax.swing.JButton();
        lblMenuInstructions = new javax.swing.JLabel();
        lblViewArtists = new javax.swing.JLabel();
        lblViewPlaylists = new javax.swing.JLabel();
        lblViewSongs = new javax.swing.JLabel();
        lblViewListeners = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        lblExit = new javax.swing.JLabel();
        lblWelcomeUser = new javax.swing.JLabel();
        JPnlArtists = new javax.swing.JPanel();
        lblLogoSongTbl = new javax.swing.JLabel();
        JScrlArtist = new javax.swing.JScrollPane();
        tblArtists = new javax.swing.JTable();
        btnBackToMenuArtist = new javax.swing.JButton();
        btnAddArtist = new javax.swing.JButton();
        btnDeleteArtist = new javax.swing.JButton();
        lblCreateArtist = new javax.swing.JLabel();
        lblDeleteArtist = new javax.swing.JLabel();
        lblBackToMenuArtist = new javax.swing.JLabel();
        btnSearchArtistPanelChange = new javax.swing.JButton();
        lblSearchArtistPanelChange = new javax.swing.JLabel();
        JPnlListeners = new javax.swing.JPanel();
        lblLogoListener = new javax.swing.JLabel();
        JScrlListeners = new javax.swing.JScrollPane();
        tblListeners = new javax.swing.JTable();
        btnBackToMenuListener = new javax.swing.JButton();
        btnCreateListener = new javax.swing.JButton();
        btnDeleteListener = new javax.swing.JButton();
        lblCreateListener = new javax.swing.JLabel();
        lblDeleteListener = new javax.swing.JLabel();
        lblBackToMenuListener = new javax.swing.JLabel();
        btnSearchListenerChangePanel = new javax.swing.JButton();
        lblSearchListenerPanelChange = new javax.swing.JLabel();
        JPnlPlaylists = new javax.swing.JPanel();
        lblLogoPlaylist = new javax.swing.JLabel();
        JScrlPlaylist = new javax.swing.JScrollPane();
        tblPlaylists = new javax.swing.JTable();
        btnBackToMenuPlaylist = new javax.swing.JButton();
        btnCreatePlaylist = new javax.swing.JButton();
        btnDeletePlaylist = new javax.swing.JButton();
        lblCreatePlaylist = new javax.swing.JLabel();
        lblBackToMenuPlaylist = new javax.swing.JLabel();
        lblDeletePlaylist = new javax.swing.JLabel();
        btnSearchPlaylistPanelChange = new javax.swing.JButton();
        lblPlaylistPanelChange = new javax.swing.JLabel();
        JPnlSongs = new javax.swing.JPanel();
        lblLogoSong = new javax.swing.JLabel();
        JScrlSong = new javax.swing.JScrollPane();
        tblSongs = new javax.swing.JTable();
        btnBackToMenuSong = new javax.swing.JButton();
        btnCreateSong = new javax.swing.JButton();
        btnDeleteSong = new javax.swing.JButton();
        lblCreateSong = new javax.swing.JLabel();
        lblDeleteSong = new javax.swing.JLabel();
        lblBackToMenuSong = new javax.swing.JLabel();
        btnSearchSongPanelChange = new javax.swing.JButton();
        lblSongSearchPanelChange = new javax.swing.JLabel();
        JPnlAddArtists = new javax.swing.JPanel();
        lblAddArtistLogo = new javax.swing.JLabel();
        lblInstructionsHeader = new javax.swing.JLabel();
        lblInstructionsAddArtist = new javax.swing.JLabel();
        txtFldArtistToBeAdded = new javax.swing.JTextField();
        txtFldLatestAlbumToBeAdded = new javax.swing.JTextField();
        txtFldAlbumSaleToBeAdded = new javax.swing.JTextField();
        txtFldPhoneNumToBeAdded = new javax.swing.JTextField();
        txtFldStreamingToBeAdded = new javax.swing.JTextField();
        txtFldMostStreamedToBeAdded = new javax.swing.JTextField();
        txtFldWebsiteToBeAdded = new javax.swing.JTextField();
        txtFldUpcomingTourToBeAdded = new javax.swing.JTextField();
        txtFldOtherOccToBeAdded = new javax.swing.JTextField();
        txtFldGenreToBeAdded = new javax.swing.JTextField();
        lblArtistToBeAdded = new javax.swing.JLabel();
        lblGrammyToBeAdded = new javax.swing.JLabel();
        lblPhoneNumToBeAdded = new javax.swing.JLabel();
        lblWebsiteToBeAdded = new javax.swing.JLabel();
        lblStreamingToBeAdded = new javax.swing.JLabel();
        lblSucAddArtist = new javax.swing.JLabel();
        lblMostStreamedToBeAdded = new javax.swing.JLabel();
        lblOtherOccupationToBeAdded = new javax.swing.JLabel();
        lblUpcomingToursToBeAdded = new javax.swing.JLabel();
        lblGenreToBeAdded = new javax.swing.JLabel();
        lblAlbumSalesToBeAdded = new javax.swing.JLabel();
        lblLatestAlbumToBeAdded = new javax.swing.JLabel();
        chkBoxGrammyNominatedToBeAdded = new javax.swing.JCheckBox();
        btnAddNewArtist = new javax.swing.JButton();
        lblInstructionsToAddArtist = new javax.swing.JLabel();
        txtFldArtistIDToBeAddedA = new javax.swing.JTextField();
        lblArtistIDToBeAddedA = new javax.swing.JLabel();
        JPnlAddListeners = new javax.swing.JPanel();
        lblLogoAddListener = new javax.swing.JLabel();
        txtFldPrefferedGenreToBeAdded = new javax.swing.JTextField();
        txtFldListenerToBeAdded = new javax.swing.JTextField();
        txtFldNameToBeAdded = new javax.swing.JTextField();
        txtFldSurnameToBeAdded = new javax.swing.JTextField();
        txtFldUsernameToBeAdded = new javax.swing.JTextField();
        lblPrefferedGenreToBeAdded = new javax.swing.JLabel();
        lblUsernameToBeAdded = new javax.swing.JLabel();
        lblSurnameToBeAdded = new javax.swing.JLabel();
        lblNameToBeAdded = new javax.swing.JLabel();
        lblListenerToBeAdded = new javax.swing.JLabel();
        lblInstructionsHederAddList = new javax.swing.JLabel();
        lblInstructionsAddList = new javax.swing.JLabel();
        btnAddListener = new javax.swing.JButton();
        lblAddListener = new javax.swing.JLabel();
        lblSucAddListener = new javax.swing.JLabel();
        txtFldListenerIDToBeAddedL = new javax.swing.JTextField();
        lblListenerIDToBeAddedL = new javax.swing.JLabel();
        JPnlAddPlaylists = new javax.swing.JPanel();
        lblLogoAddPlaylist = new javax.swing.JLabel();
        lblListenerIDToBeAdded = new javax.swing.JLabel();
        lblSongIDToBeAdded = new javax.swing.JLabel();
        txtFldListenerIDToBeAdded = new javax.swing.JTextField();
        txtFldSongIDToBeAdded = new javax.swing.JTextField();
        txtFldInstructionsHeaderAddPlaylist = new javax.swing.JLabel();
        lblInstructionsAddPlaylist = new javax.swing.JLabel();
        btnAddPlaylist = new javax.swing.JButton();
        lblInstructionsAddPlaylistBtn = new javax.swing.JLabel();
        lblSucAddPlaylist = new javax.swing.JLabel();
        JPnlAddSongs = new javax.swing.JPanel();
        lblLogoAddSong = new javax.swing.JLabel();
        lblArtistIDToBeAdded = new javax.swing.JLabel();
        lblSongNameToBeAdded = new javax.swing.JLabel();
        lblReleaseDateToBeAdded = new javax.swing.JLabel();
        lblInstructionsHeaderAddSong = new javax.swing.JLabel();
        lblInstructionsAddSong = new javax.swing.JLabel();
        lblAlbumToBeAdded = new javax.swing.JLabel();
        lblGenreToBeAddedSong = new javax.swing.JLabel();
        txtFldSongNameToBeAdded = new javax.swing.JTextField();
        txtFldArtistIDToBeAdded = new javax.swing.JTextField();
        txtFldGenreToBeAddedSong = new javax.swing.JTextField();
        txtFldAlbumToBeAdded = new javax.swing.JTextField();
        lblSucAddSong = new javax.swing.JLabel();
        btnAddSong = new javax.swing.JButton();
        lblAddSongInstructions = new javax.swing.JLabel();
        txtFldDurationToBeAdded = new javax.swing.JTextField();
        lblDurationToBeAdded = new javax.swing.JLabel();
        songReleaseDateToBeAdded = new com.toedter.calendar.JDateChooser();
        JPnlArtistSearch = new javax.swing.JPanel();
        lblSearchArtistLogo = new javax.swing.JLabel();
        JScrlSearchArtist = new javax.swing.JScrollPane();
        tblSearchArtist = new javax.swing.JTable();
        lblSearchArtist = new javax.swing.JLabel();
        txtFldArtistToSearchFor = new javax.swing.JTextField();
        btnSearchArtist = new javax.swing.JButton();
        btnBackToArtistTblSearch = new javax.swing.JButton();
        lblInstructionsSearchArtist = new javax.swing.JLabel();
        lblActualInstructionsArtistSearch = new javax.swing.JLabel();
        lblSearchArtistBtn = new javax.swing.JLabel();
        lblBackToArtistTblSearch = new javax.swing.JLabel();
        lblFoundArtist = new javax.swing.JLabel();
        lblNotFoundArtist = new javax.swing.JLabel();
        JPnlListenerSearch = new javax.swing.JPanel();
        lblSearchListenerLogo = new javax.swing.JLabel();
        JScrlSearchListener = new javax.swing.JScrollPane();
        tblSearchListener = new javax.swing.JTable();
        lblSearchListener = new javax.swing.JLabel();
        txtFldListenerToSearchFor = new javax.swing.JTextField();
        btnSearchListener = new javax.swing.JButton();
        btnBackToListenerTblSearch = new javax.swing.JButton();
        lblInstructionsSearchListener = new javax.swing.JLabel();
        lblActualInstructionsListenerSearch = new javax.swing.JLabel();
        lblSearchListenerBtn = new javax.swing.JLabel();
        lblBackToListenerTblSearch = new javax.swing.JLabel();
        lblFoundListener = new javax.swing.JLabel();
        lblListenerNotFound = new javax.swing.JLabel();
        JPnlPlaylistSearch = new javax.swing.JPanel();
        lblSearchPlaylistLogo = new javax.swing.JLabel();
        JScrlSearchPlaylist = new javax.swing.JScrollPane();
        tblSearchPlaylist = new javax.swing.JTable();
        lblSearchPlaylist = new javax.swing.JLabel();
        txtFldPlaylistToSearchFor = new javax.swing.JTextField();
        btnSearchPlaylist = new javax.swing.JButton();
        btnBackToPlaylistTblSearch = new javax.swing.JButton();
        lblInstructionsSearchPlaylist = new javax.swing.JLabel();
        lblActualInstructionsPlaylistSearch = new javax.swing.JLabel();
        btnSearchInstructions = new javax.swing.JLabel();
        lblBackToPlaylistTblSearch = new javax.swing.JLabel();
        lblFoundPlaylist = new javax.swing.JLabel();
        lblNotFoundPlaylist = new javax.swing.JLabel();
        JPnlSongSearch = new javax.swing.JPanel();
        lblSearchSongLogo = new javax.swing.JLabel();
        JScrlSearchSong = new javax.swing.JScrollPane();
        tblSearchSong = new javax.swing.JTable();
        lblSearchSong = new javax.swing.JLabel();
        txtFldSongToSearchFor = new javax.swing.JTextField();
        btnSearchSong = new javax.swing.JButton();
        btnBackToSongTblSearch = new javax.swing.JButton();
        lblInstructionsSearchSong = new javax.swing.JLabel();
        lblActualInstructionsSongSearch = new javax.swing.JLabel();
        lblSearchArtistIDBtn = new javax.swing.JLabel();
        lblBackToSongtblSearch = new javax.swing.JLabel();
        lblFoundSong = new javax.swing.JLabel();
        lblNotFoundSong = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        JPnlLogin.setBackground(new java.awt.Color(0, 0, 204));
        JPnlLogin.setMaximumSize(new java.awt.Dimension(100, 100));
        JPnlLogin.setMinimumSize(new java.awt.Dimension(300, 300));
        JPnlLogin.setPreferredSize(new java.awt.Dimension(400, 400));

        lblLogoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        lblUsernameLogin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblUsernameLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblUsernameLogin.setText("Enter username:");

        lblPasswordLogin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblPasswordLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblPasswordLogin.setText("Enter password:");

        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnAddUser.setText("ADD");
        btnAddUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnHelp.setText("HELP");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });

        lblInstructionAdd.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblInstructionAdd.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionAdd.setText("If you don't have a username and password press this button");

        lblHelp.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblHelp.setForeground(new java.awt.Color(255, 255, 255));
        lblHelp.setText("If confused press this button");

        lblErrorMessage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblErrorMessage.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout JPnlLoginLayout = new javax.swing.GroupLayout(JPnlLogin);
        JPnlLogin.setLayout(JPnlLoginLayout);
        JPnlLoginLayout.setHorizontalGroup(
            JPnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlLoginLayout.createSequentialGroup()
                .addGroup(JPnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlLoginLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(lblPasswordLogin))
                    .addGroup(JPnlLoginLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lblHelp))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlLoginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblUsernameLogin)))
                .addGroup(JPnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlLoginLayout.createSequentialGroup()
                        .addGroup(JPnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPnlLoginLayout.createSequentialGroup()
                                .addGroup(JPnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblLogoLogin)
                                    .addGroup(JPnlLoginLayout.createSequentialGroup()
                                        .addComponent(btnLogin)
                                        .addGap(52, 52, 52)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlLoginLayout.createSequentialGroup()
                                .addGap(0, 245, Short.MAX_VALUE)
                                .addComponent(lblInstructionAdd)))
                        .addGap(184, 256, Short.MAX_VALUE))
                    .addGroup(JPnlLoginLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(JPnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(JPnlLoginLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(btnHelp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddUser)
                .addGap(187, 187, 187))
            .addGroup(JPnlLoginLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(lblErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPnlLoginLayout.setVerticalGroup(
            JPnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlLoginLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblLogoLogin)
                .addGap(55, 55, 55)
                .addGroup(JPnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsernameLogin))
                .addGap(18, 18, 18)
                .addGroup(JPnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPasswordLogin)
                    .addComponent(txtFldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btnLogin)
                .addGap(18, 18, 18)
                .addComponent(lblErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addGroup(JPnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHelp)
                    .addComponent(lblInstructionAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddUser, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnHelp))
                .addContainerGap())
        );

        getContentPane().add(JPnlLogin, "card2");

        JPnlAddUser.setBackground(new java.awt.Color(0, 0, 204));

        lblLogoAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        lblAddUsername.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblAddUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblAddUsername.setText("Add username:");

        txtFldAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldAddUserActionPerformed(evt);
            }
        });

        lblAddPassword.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblAddPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblAddPassword.setText("Add password:");

        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblAddUserSuc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAddUserSuc.setForeground(new java.awt.Color(51, 255, 0));

        lblBackToLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBackToLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToLogin.setText("When done adding user click this BACK button");

        javax.swing.GroupLayout JPnlAddUserLayout = new javax.swing.GroupLayout(JPnlAddUser);
        JPnlAddUser.setLayout(JPnlAddUserLayout);
        JPnlAddUserLayout.setHorizontalGroup(
            JPnlAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlAddUserLayout.createSequentialGroup()
                .addGroup(JPnlAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlAddUserLayout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(lblLogoAddUser))
                    .addGroup(JPnlAddUserLayout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addGroup(JPnlAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAddPassword)
                            .addComponent(lblAddUsername))
                        .addGap(18, 18, 18)
                        .addGroup(JPnlAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFldAddUser, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txtFldAddPassword)))
                    .addGroup(JPnlAddUserLayout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(lblAddUserSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPnlAddUserLayout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(btnAdd)))
                .addContainerGap(206, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddUserLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(JPnlAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddUserLayout.createSequentialGroup()
                        .addComponent(lblBackToLogin)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddUserLayout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(133, 133, 133))))
        );
        JPnlAddUserLayout.setVerticalGroup(
            JPnlAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlAddUserLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblLogoAddUser)
                .addGap(41, 41, 41)
                .addGroup(JPnlAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddUsername)
                    .addComponent(txtFldAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddPassword)
                    .addComponent(txtFldAddPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAddUserSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(lblBackToLogin)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap())
        );

        getContentPane().add(JPnlAddUser, "card3");

        JPnlMainMenu.setBackground(new java.awt.Color(0, 0, 204));

        lblLogoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        btnArtists.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnArtists.setText("ARTISTS");
        btnArtists.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArtistsActionPerformed(evt);
            }
        });

        btnPlaylists.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnPlaylists.setText("PLAYLISTS");
        btnPlaylists.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaylistsActionPerformed(evt);
            }
        });

        btnListeners.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnListeners.setText("LISTENERS");
        btnListeners.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListenersActionPerformed(evt);
            }
        });

        btnSongs.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSongs.setText("SONGS");
        btnSongs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSongsActionPerformed(evt);
            }
        });

        lblMenuInstructions.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblMenuInstructions.setForeground(new java.awt.Color(255, 255, 255));
        lblMenuInstructions.setText("SELECT A TABLE TO VIEW:");

        lblViewArtists.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblViewArtists.setForeground(new java.awt.Color(255, 255, 255));
        lblViewArtists.setText("Click button to view artists");

        lblViewPlaylists.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblViewPlaylists.setForeground(new java.awt.Color(255, 255, 255));
        lblViewPlaylists.setText("Click button to view playlists");

        lblViewSongs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblViewSongs.setForeground(new java.awt.Color(255, 255, 255));
        lblViewSongs.setText("Click button to view songs");

        lblViewListeners.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblViewListeners.setForeground(new java.awt.Color(255, 255, 255));
        lblViewListeners.setText("Click button to view listeners");

        btnExit.setText("CLOSE");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblExit.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblExit.setForeground(new java.awt.Color(255, 255, 255));
        lblExit.setText("Exit progam:");

        lblWelcomeUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblWelcomeUser.setForeground(new java.awt.Color(0, 255, 0));

        javax.swing.GroupLayout JPnlMainMenuLayout = new javax.swing.GroupLayout(JPnlMainMenu);
        JPnlMainMenu.setLayout(JPnlMainMenuLayout);
        JPnlMainMenuLayout.setHorizontalGroup(
            JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlMainMenuLayout.createSequentialGroup()
                .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlMainMenuLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnArtists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnListeners, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)))
                    .addGroup(JPnlMainMenuLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblViewListeners)
                            .addComponent(lblViewArtists))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlMainMenuLayout.createSequentialGroup()
                        .addComponent(lblViewPlaylists)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JPnlMainMenuLayout.createSequentialGroup()
                        .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlMainMenuLayout.createSequentialGroup()
                                .addComponent(lblViewSongs)
                                .addGap(96, 96, 96))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlMainMenuLayout.createSequentialGroup()
                                .addComponent(btnSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlMainMenuLayout.createSequentialGroup()
                                .addComponent(btnPlaylists, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(131, 131, 131)))
                        .addGap(0, 61, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlMainMenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblWelcomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(JPnlMainMenuLayout.createSequentialGroup()
                .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlMainMenuLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblExit)
                        .addGap(18, 18, 18)
                        .addComponent(btnExit))
                    .addGroup(JPnlMainMenuLayout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMenuInstructions)
                            .addGroup(JPnlMainMenuLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(lblLogoMenu)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JPnlMainMenuLayout.setVerticalGroup(
            JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlMainMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogoMenu)
                .addGap(14, 14, 14)
                .addComponent(lblMenuInstructions)
                .addGap(18, 18, 18)
                .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArtists, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlaylists, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblViewArtists)
                    .addComponent(lblViewPlaylists))
                .addGap(34, 34, 34)
                .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListeners, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblViewSongs)
                    .addComponent(lblViewListeners))
                .addContainerGap(134, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlMainMenuLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(lblWelcomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(lblExit))
                .addContainerGap())
        );

        getContentPane().add(JPnlMainMenu, "card4");

        JPnlArtists.setBackground(new java.awt.Color(0, 0, 204));

        lblLogoSongTbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        tblArtists.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "artistID", "artist", "grammyNomindated", "latestAlbum", "genre", "albumSales", "upcomingTours", "phoneNum", "website", "streamingPlatforms", "mostStreamedSong", "otherOccupation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JScrlArtist.setViewportView(tblArtists);
        if (tblArtists.getColumnModel().getColumnCount() > 0) {
            tblArtists.getColumnModel().getColumn(0).setResizable(false);
        }

        btnBackToMenuArtist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBackToMenuArtist.setText("BACK");
        btnBackToMenuArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMenuArtistActionPerformed(evt);
            }
        });

        btnAddArtist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddArtist.setText("CREATE");
        btnAddArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddArtistActionPerformed(evt);
            }
        });

        btnDeleteArtist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDeleteArtist.setText("DELETE");
        btnDeleteArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteArtistActionPerformed(evt);
            }
        });

        lblCreateArtist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCreateArtist.setForeground(new java.awt.Color(255, 255, 255));
        lblCreateArtist.setText("Click button to create your own artist");

        lblDeleteArtist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDeleteArtist.setForeground(new java.awt.Color(255, 255, 255));
        lblDeleteArtist.setText("Click this button to delete an artist in the table");

        lblBackToMenuArtist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBackToMenuArtist.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToMenuArtist.setText("Click button to go back to main menu");

        btnSearchArtistPanelChange.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearchArtistPanelChange.setText("SEARCH");
        btnSearchArtistPanelChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchArtistPanelChangeActionPerformed(evt);
            }
        });

        lblSearchArtistPanelChange.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSearchArtistPanelChange.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchArtistPanelChange.setText("Click this button to search for an artist");

        javax.swing.GroupLayout JPnlArtistsLayout = new javax.swing.GroupLayout(JPnlArtists);
        JPnlArtists.setLayout(JPnlArtistsLayout);
        JPnlArtistsLayout.setHorizontalGroup(
            JPnlArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlArtistsLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(btnBackToMenuArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(233, 233, 233)
                .addComponent(btnAddArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
            .addGroup(JPnlArtistsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JScrlArtist)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlArtistsLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(lblBackToMenuArtist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDeleteArtist)
                .addGap(63, 63, 63))
            .addGroup(JPnlArtistsLayout.createSequentialGroup()
                .addGroup(JPnlArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlArtistsLayout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(lblLogoSongTbl)
                        .addGroup(JPnlArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPnlArtistsLayout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(btnSearchArtistPanelChange, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPnlArtistsLayout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(lblSearchArtistPanelChange))))
                    .addGroup(JPnlArtistsLayout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(lblCreateArtist)))
                .addContainerGap(264, Short.MAX_VALUE))
        );
        JPnlArtistsLayout.setVerticalGroup(
            JPnlArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlArtistsLayout.createSequentialGroup()
                .addGroup(JPnlArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlArtistsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogoSongTbl))
                    .addGroup(JPnlArtistsLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(lblSearchArtistPanelChange)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchArtistPanelChange, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(JScrlArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCreateArtist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPnlArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBackToMenuArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPnlArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDeleteArtist)
                    .addComponent(lblBackToMenuArtist))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        getContentPane().add(JPnlArtists, "card5");

        JPnlListeners.setBackground(new java.awt.Color(0, 0, 204));

        lblLogoListener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        tblListeners.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "listenerID", "listener", "name", "surname", "prefferedGenre", "username"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JScrlListeners.setViewportView(tblListeners);

        btnBackToMenuListener.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBackToMenuListener.setText("BACK");
        btnBackToMenuListener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMenuListenerActionPerformed(evt);
            }
        });

        btnCreateListener.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCreateListener.setText("CREATE");
        btnCreateListener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateListenerActionPerformed(evt);
            }
        });

        btnDeleteListener.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDeleteListener.setText("DELETE");
        btnDeleteListener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteListenerActionPerformed(evt);
            }
        });

        lblCreateListener.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCreateListener.setForeground(new java.awt.Color(255, 255, 255));
        lblCreateListener.setText("Click this button to create your own listener");

        lblDeleteListener.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDeleteListener.setForeground(new java.awt.Color(255, 255, 255));
        lblDeleteListener.setText("Click button to delete a listener");

        lblBackToMenuListener.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBackToMenuListener.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToMenuListener.setText("Click button to go back to main menu");

        btnSearchListenerChangePanel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearchListenerChangePanel.setText("SEARCH");
        btnSearchListenerChangePanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchListenerChangePanelActionPerformed(evt);
            }
        });

        lblSearchListenerPanelChange.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSearchListenerPanelChange.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchListenerPanelChange.setText("Click this button to search for a listener");

        javax.swing.GroupLayout JPnlListenersLayout = new javax.swing.GroupLayout(JPnlListeners);
        JPnlListeners.setLayout(JPnlListenersLayout);
        JPnlListenersLayout.setHorizontalGroup(
            JPnlListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlListenersLayout.createSequentialGroup()
                .addGroup(JPnlListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlListenersLayout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(lblLogoListener)
                        .addGroup(JPnlListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPnlListenersLayout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(btnSearchListenerChangePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPnlListenersLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(lblSearchListenerPanelChange))))
                    .addGroup(JPnlListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(JPnlListenersLayout.createSequentialGroup()
                            .addGap(52, 52, 52)
                            .addComponent(lblBackToMenuListener)
                            .addGap(337, 337, 337)
                            .addComponent(lblDeleteListener))
                        .addGroup(JPnlListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPnlListenersLayout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(btnBackToMenuListener, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(147, 147, 147)
                                .addComponent(btnCreateListener, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addComponent(btnDeleteListener, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPnlListenersLayout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(JScrlListeners, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPnlListenersLayout.createSequentialGroup()
                                .addGap(312, 312, 312)
                                .addComponent(lblCreateListener)))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        JPnlListenersLayout.setVerticalGroup(
            JPnlListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlListenersLayout.createSequentialGroup()
                .addGroup(JPnlListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlListenersLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogoListener))
                    .addGroup(JPnlListenersLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(lblSearchListenerPanelChange)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearchListenerChangePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addComponent(JScrlListeners, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCreateListener)
                .addGap(18, 18, 18)
                .addGroup(JPnlListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBackToMenuListener, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateListener, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteListener, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPnlListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDeleteListener)
                    .addComponent(lblBackToMenuListener))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        getContentPane().add(JPnlListeners, "card6");

        JPnlPlaylists.setBackground(new java.awt.Color(0, 0, 204));

        lblLogoPlaylist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        tblPlaylists.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "listenerID", "songID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JScrlPlaylist.setViewportView(tblPlaylists);

        btnBackToMenuPlaylist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBackToMenuPlaylist.setText("BACK");
        btnBackToMenuPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMenuPlaylistActionPerformed(evt);
            }
        });

        btnCreatePlaylist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCreatePlaylist.setText("CREATE");
        btnCreatePlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePlaylistActionPerformed(evt);
            }
        });

        btnDeletePlaylist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDeletePlaylist.setText("DELETE");
        btnDeletePlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePlaylistActionPerformed(evt);
            }
        });

        lblCreatePlaylist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCreatePlaylist.setForeground(new java.awt.Color(255, 255, 255));
        lblCreatePlaylist.setText("Click button to create your own playlist");

        lblBackToMenuPlaylist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBackToMenuPlaylist.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToMenuPlaylist.setText("Click button to go back to main menu");

        lblDeletePlaylist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDeletePlaylist.setForeground(new java.awt.Color(255, 255, 255));
        lblDeletePlaylist.setText("Click button to delete a playlist");

        btnSearchPlaylistPanelChange.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearchPlaylistPanelChange.setText("SEARCH");
        btnSearchPlaylistPanelChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPlaylistPanelChangeActionPerformed(evt);
            }
        });

        lblPlaylistPanelChange.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlaylistPanelChange.setForeground(new java.awt.Color(255, 255, 255));
        lblPlaylistPanelChange.setText("Click this button to search for a playlist");

        javax.swing.GroupLayout JPnlPlaylistsLayout = new javax.swing.GroupLayout(JPnlPlaylists);
        JPnlPlaylists.setLayout(JPnlPlaylistsLayout);
        JPnlPlaylistsLayout.setHorizontalGroup(
            JPnlPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlPlaylistsLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(btnBackToMenuPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134)
                .addComponent(btnCreatePlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeletePlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlPlaylistsLayout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addGroup(JPnlPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlPlaylistsLayout.createSequentialGroup()
                        .addComponent(lblLogoPlaylist)
                        .addGroup(JPnlPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPnlPlaylistsLayout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(btnSearchPlaylistPanelChange, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlPlaylistsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPlaylistPanelChange)
                                .addGap(23, 23, 23))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlPlaylistsLayout.createSequentialGroup()
                        .addGroup(JPnlPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDeletePlaylist)
                            .addComponent(JScrlPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66))))
            .addGroup(JPnlPlaylistsLayout.createSequentialGroup()
                .addGroup(JPnlPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlPlaylistsLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(lblBackToMenuPlaylist))
                    .addGroup(JPnlPlaylistsLayout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(lblCreatePlaylist)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPnlPlaylistsLayout.setVerticalGroup(
            JPnlPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlPlaylistsLayout.createSequentialGroup()
                .addGroup(JPnlPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlPlaylistsLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(lblPlaylistPanelChange)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchPlaylistPanelChange, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPnlPlaylistsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogoPlaylist)))
                .addGap(32, 32, 32)
                .addComponent(JScrlPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblCreatePlaylist)
                .addGap(18, 18, 18)
                .addGroup(JPnlPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDeletePlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(btnBackToMenuPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCreatePlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(JPnlPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBackToMenuPlaylist)
                    .addComponent(lblDeletePlaylist))
                .addContainerGap())
        );

        getContentPane().add(JPnlPlaylists, "card7");

        JPnlSongs.setBackground(new java.awt.Color(0, 0, 204));

        lblLogoSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        tblSongs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "artistID", "songName", "releaseDate", "genre", "duration", "album"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JScrlSong.setViewportView(tblSongs);

        btnBackToMenuSong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBackToMenuSong.setText("BACK");
        btnBackToMenuSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMenuSongActionPerformed(evt);
            }
        });

        btnCreateSong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCreateSong.setText("CREATE");
        btnCreateSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateSongActionPerformed(evt);
            }
        });

        btnDeleteSong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDeleteSong.setText("DELETE");
        btnDeleteSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSongActionPerformed(evt);
            }
        });

        lblCreateSong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCreateSong.setForeground(new java.awt.Color(255, 255, 255));
        lblCreateSong.setText("Click this button to create your own song");

        lblDeleteSong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDeleteSong.setForeground(new java.awt.Color(255, 255, 255));
        lblDeleteSong.setText("Click this button to delete a song");

        lblBackToMenuSong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBackToMenuSong.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToMenuSong.setText("Click this button to go back to the main menu");

        btnSearchSongPanelChange.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearchSongPanelChange.setText("SEARCH");
        btnSearchSongPanelChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSongPanelChangeActionPerformed(evt);
            }
        });

        lblSongSearchPanelChange.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSongSearchPanelChange.setForeground(new java.awt.Color(255, 255, 255));
        lblSongSearchPanelChange.setText("Click this button to search for a song");

        javax.swing.GroupLayout JPnlSongsLayout = new javax.swing.GroupLayout(JPnlSongs);
        JPnlSongs.setLayout(JPnlSongsLayout);
        JPnlSongsLayout.setHorizontalGroup(
            JPnlSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlSongsLayout.createSequentialGroup()
                .addGroup(JPnlSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JPnlSongsLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(btnBackToMenuSong, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(btnCreateSong, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteSong, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JPnlSongsLayout.createSequentialGroup()
                        .addGroup(JPnlSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JPnlSongsLayout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(lblLogoSong)
                                .addGroup(JPnlSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPnlSongsLayout.createSequentialGroup()
                                        .addGap(123, 123, 123)
                                        .addComponent(btnSearchSongPanelChange, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JPnlSongsLayout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(lblSongSearchPanelChange))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JPnlSongsLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(JScrlSong, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(JPnlSongsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblBackToMenuSong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDeleteSong)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlSongsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCreateSong)
                .addGap(203, 203, 203))
        );
        JPnlSongsLayout.setVerticalGroup(
            JPnlSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlSongsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPnlSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLogoSong)
                    .addGroup(JPnlSongsLayout.createSequentialGroup()
                        .addComponent(lblSongSearchPanelChange)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchSongPanelChange, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(JScrlSong, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblCreateSong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPnlSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateSong, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteSong, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBackToMenuSong, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPnlSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBackToMenuSong)
                    .addComponent(lblDeleteSong, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(JPnlSongs, "card8");

        JPnlAddArtists.setBackground(new java.awt.Color(0, 0, 204));

        lblAddArtistLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        lblInstructionsHeader.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lblInstructionsHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsHeader.setText("Instructions:");

        lblInstructionsAddArtist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblInstructionsAddArtist.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsAddArtist.setText("Enter valid values into the textField to create your own artist");

        lblArtistToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblArtistToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblArtistToBeAdded.setText("ArtistName:");

        lblGrammyToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGrammyToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblGrammyToBeAdded.setText("GrammyNominated:");

        lblPhoneNumToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPhoneNumToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblPhoneNumToBeAdded.setText("PhoneNumber:");

        lblWebsiteToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblWebsiteToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblWebsiteToBeAdded.setText("Website:");

        lblStreamingToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStreamingToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblStreamingToBeAdded.setText("StreamingPlatforms:");

        lblSucAddArtist.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblSucAddArtist.setForeground(new java.awt.Color(0, 255, 0));

        lblMostStreamedToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMostStreamedToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblMostStreamedToBeAdded.setText("MostStreamedSongs:");

        lblOtherOccupationToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOtherOccupationToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblOtherOccupationToBeAdded.setText("OtherOccupation:");

        lblUpcomingToursToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUpcomingToursToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblUpcomingToursToBeAdded.setText("UpcomingTours:");

        lblGenreToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGenreToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblGenreToBeAdded.setText("Genre:");

        lblAlbumSalesToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAlbumSalesToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblAlbumSalesToBeAdded.setText("AlbumSales:");

        lblLatestAlbumToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLatestAlbumToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblLatestAlbumToBeAdded.setText("LatestAlbum:");

        chkBoxGrammyNominatedToBeAdded.setBackground(new java.awt.Color(0, 0, 204));

        btnAddNewArtist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddNewArtist.setText("ADD");
        btnAddNewArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewArtistActionPerformed(evt);
            }
        });

        lblInstructionsToAddArtist.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblInstructionsToAddArtist.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsToAddArtist.setText("When done adding variables click this button to add artist to table");

        lblArtistIDToBeAddedA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblArtistIDToBeAddedA.setForeground(new java.awt.Color(255, 255, 255));
        lblArtistIDToBeAddedA.setText("Enter an ArtistID (that hasn't been used):");

        javax.swing.GroupLayout JPnlAddArtistsLayout = new javax.swing.GroupLayout(JPnlAddArtists);
        JPnlAddArtists.setLayout(JPnlAddArtistsLayout);
        JPnlAddArtistsLayout.setHorizontalGroup(
            JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlAddArtistsLayout.createSequentialGroup()
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlAddArtistsLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(lblInstructionsHeader))
                    .addGroup(JPnlAddArtistsLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblInstructionsAddArtist)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
                .addComponent(lblAddArtistLogo)
                .addGap(166, 166, 166))
            .addGroup(JPnlAddArtistsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddArtistsLayout.createSequentialGroup()
                        .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLatestAlbumToBeAdded, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblGrammyToBeAdded, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblGenreToBeAdded, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAlbumSalesToBeAdded, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblUpcomingToursToBeAdded, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddArtistsLayout.createSequentialGroup()
                                .addComponent(lblSucAddArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblArtistIDToBeAddedA)))
                        .addGap(18, 18, 18)
                        .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFldLatestAlbumToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFldGenreToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFldAlbumSaleToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFldUpcomingTourToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkBoxGrammyNominatedToBeAdded)
                            .addComponent(txtFldArtistIDToBeAddedA, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddArtistsLayout.createSequentialGroup()
                        .addComponent(lblArtistToBeAdded)
                        .addGap(18, 18, 18)
                        .addComponent(txtFldArtistToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWebsiteToBeAdded, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPhoneNumToBeAdded, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStreamingToBeAdded, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMostStreamedToBeAdded, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOtherOccupationToBeAdded, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFldPhoneNumToBeAdded)
                    .addComponent(txtFldWebsiteToBeAdded)
                    .addComponent(txtFldStreamingToBeAdded)
                    .addComponent(txtFldMostStreamedToBeAdded)
                    .addComponent(txtFldOtherOccToBeAdded, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddArtistsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddArtistsLayout.createSequentialGroup()
                        .addComponent(btnAddNewArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddArtistsLayout.createSequentialGroup()
                        .addComponent(lblInstructionsToAddArtist)
                        .addContainerGap())))
        );
        JPnlAddArtistsLayout.setVerticalGroup(
            JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlAddArtistsLayout.createSequentialGroup()
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlAddArtistsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAddArtistLogo))
                    .addGroup(JPnlAddArtistsLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(lblInstructionsHeader)
                        .addGap(18, 18, 18)
                        .addComponent(lblInstructionsAddArtist)))
                .addGap(54, 54, 54)
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldArtistToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFldPhoneNumToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblArtistToBeAdded)
                    .addComponent(lblPhoneNumToBeAdded))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldWebsiteToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGrammyToBeAdded)
                    .addComponent(lblWebsiteToBeAdded)
                    .addComponent(chkBoxGrammyNominatedToBeAdded))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldLatestAlbumToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFldStreamingToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLatestAlbumToBeAdded)
                    .addComponent(lblStreamingToBeAdded))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldGenreToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFldMostStreamedToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenreToBeAdded)
                    .addComponent(lblMostStreamedToBeAdded))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldAlbumSaleToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFldOtherOccToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlbumSalesToBeAdded)
                    .addComponent(lblOtherOccupationToBeAdded))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldUpcomingTourToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUpcomingToursToBeAdded))
                .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlAddArtistsLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPnlAddArtistsLayout.createSequentialGroup()
                                .addComponent(lblSucAddArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 71, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddArtistsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblInstructionsToAddArtist)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddNewArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(JPnlAddArtistsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(JPnlAddArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFldArtistIDToBeAddedA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArtistIDToBeAddedA))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getContentPane().add(JPnlAddArtists, "card9");

        JPnlAddListeners.setBackground(new java.awt.Color(0, 0, 204));

        lblLogoAddListener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        lblPrefferedGenreToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPrefferedGenreToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblPrefferedGenreToBeAdded.setText("PerfferedGenre:");

        lblUsernameToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUsernameToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblUsernameToBeAdded.setText("Username:");

        lblSurnameToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSurnameToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblSurnameToBeAdded.setText("Surname:");

        lblNameToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNameToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblNameToBeAdded.setText("Name:");

        lblListenerToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblListenerToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblListenerToBeAdded.setText("Listener:");

        lblInstructionsHederAddList.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lblInstructionsHederAddList.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsHederAddList.setText("Instructions:");

        lblInstructionsAddList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblInstructionsAddList.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsAddList.setText("Enter valid variables to create your own Listener");

        btnAddListener.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddListener.setText("ADD");
        btnAddListener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddListenerActionPerformed(evt);
            }
        });

        lblAddListener.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAddListener.setForeground(new java.awt.Color(255, 255, 255));
        lblAddListener.setText("When done adding variables click this button to add info into listener table");

        lblSucAddListener.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSucAddListener.setForeground(new java.awt.Color(0, 255, 0));

        lblListenerIDToBeAddedL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblListenerIDToBeAddedL.setForeground(new java.awt.Color(255, 255, 255));
        lblListenerIDToBeAddedL.setText("ListenerID (that hasn't been used before):");

        javax.swing.GroupLayout JPnlAddListenersLayout = new javax.swing.GroupLayout(JPnlAddListeners);
        JPnlAddListeners.setLayout(JPnlAddListenersLayout);
        JPnlAddListenersLayout.setHorizontalGroup(
            JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddListenersLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(lblSucAddListener, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddListener, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddListenersLayout.createSequentialGroup()
                .addContainerGap(448, Short.MAX_VALUE)
                .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddListenersLayout.createSequentialGroup()
                        .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddListenersLayout.createSequentialGroup()
                                .addComponent(lblInstructionsHederAddList)
                                .addGap(108, 108, 108))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddListenersLayout.createSequentialGroup()
                                .addComponent(lblInstructionsAddList)
                                .addGap(18, 18, 18)))
                        .addComponent(lblLogoAddListener)
                        .addGap(200, 200, 200))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddListenersLayout.createSequentialGroup()
                        .addComponent(lblAddListener)
                        .addContainerGap())))
            .addGroup(JPnlAddListenersLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUsernameToBeAdded)
                    .addComponent(lblPrefferedGenreToBeAdded)
                    .addComponent(lblSurnameToBeAdded)
                    .addComponent(lblNameToBeAdded)
                    .addComponent(lblListenerToBeAdded)
                    .addComponent(lblListenerIDToBeAddedL))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFldNameToBeAdded, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(txtFldListenerToBeAdded, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(txtFldSurnameToBeAdded, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(txtFldPrefferedGenreToBeAdded, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(txtFldUsernameToBeAdded, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(txtFldListenerIDToBeAddedL))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPnlAddListenersLayout.setVerticalGroup(
            JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlAddListenersLayout.createSequentialGroup()
                .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlAddListenersLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogoAddListener))
                    .addGroup(JPnlAddListenersLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(lblInstructionsHederAddList)
                        .addGap(18, 18, 18)
                        .addComponent(lblInstructionsAddList)))
                .addGap(54, 54, 54)
                .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldListenerIDToBeAddedL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblListenerIDToBeAddedL))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldListenerToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblListenerToBeAdded))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldNameToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNameToBeAdded))
                .addGap(21, 21, 21)
                .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSurnameToBeAdded)
                    .addComponent(txtFldSurnameToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldPrefferedGenreToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrefferedGenreToBeAdded))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldUsernameToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsernameToBeAdded))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAddListener)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(JPnlAddListenersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAddListener, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPnlAddListenersLayout.createSequentialGroup()
                        .addComponent(lblSucAddListener, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addGap(27, 27, 27))
        );

        getContentPane().add(JPnlAddListeners, "card10");

        JPnlAddPlaylists.setBackground(new java.awt.Color(0, 0, 204));

        lblLogoAddPlaylist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        lblListenerIDToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblListenerIDToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblListenerIDToBeAdded.setText("ListenerID");

        lblSongIDToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSongIDToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblSongIDToBeAdded.setText("SongID:");

        txtFldInstructionsHeaderAddPlaylist.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        txtFldInstructionsHeaderAddPlaylist.setForeground(new java.awt.Color(255, 255, 255));
        txtFldInstructionsHeaderAddPlaylist.setText("Instructions:");

        lblInstructionsAddPlaylist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblInstructionsAddPlaylist.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsAddPlaylist.setText("Enter valid variables to create your own playlist");

        btnAddPlaylist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddPlaylist.setText("ADD");
        btnAddPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPlaylistActionPerformed(evt);
            }
        });

        lblInstructionsAddPlaylistBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblInstructionsAddPlaylistBtn.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsAddPlaylistBtn.setText("When done adding variables click this button to add new playlist to the table");

        javax.swing.GroupLayout JPnlAddPlaylistsLayout = new javax.swing.GroupLayout(JPnlAddPlaylists);
        JPnlAddPlaylists.setLayout(JPnlAddPlaylistsLayout);
        JPnlAddPlaylistsLayout.setHorizontalGroup(
            JPnlAddPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlAddPlaylistsLayout.createSequentialGroup()
                .addGroup(JPnlAddPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlAddPlaylistsLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addGroup(JPnlAddPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSongIDToBeAdded)
                            .addComponent(lblListenerIDToBeAdded))
                        .addGap(18, 18, 18)
                        .addGroup(JPnlAddPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFldSongIDToBeAdded, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(txtFldListenerIDToBeAdded)))
                    .addGroup(JPnlAddPlaylistsLayout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(btnAddPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPnlAddPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(JPnlAddPlaylistsLayout.createSequentialGroup()
                            .addGroup(JPnlAddPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(JPnlAddPlaylistsLayout.createSequentialGroup()
                                    .addGap(98, 98, 98)
                                    .addComponent(txtFldInstructionsHeaderAddPlaylist))
                                .addGroup(JPnlAddPlaylistsLayout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(lblInstructionsAddPlaylist)))
                            .addGap(82, 82, 82)
                            .addComponent(lblLogoAddPlaylist))
                        .addGroup(JPnlAddPlaylistsLayout.createSequentialGroup()
                            .addGap(74, 74, 74)
                            .addGroup(JPnlAddPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblInstructionsAddPlaylistBtn)
                                .addGroup(JPnlAddPlaylistsLayout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addComponent(lblSucAddPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(570, Short.MAX_VALUE))
        );
        JPnlAddPlaylistsLayout.setVerticalGroup(
            JPnlAddPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlAddPlaylistsLayout.createSequentialGroup()
                .addGroup(JPnlAddPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlAddPlaylistsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogoAddPlaylist))
                    .addGroup(JPnlAddPlaylistsLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(txtFldInstructionsHeaderAddPlaylist)
                        .addGap(18, 18, 18)
                        .addComponent(lblInstructionsAddPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(JPnlAddPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblListenerIDToBeAdded)
                    .addComponent(txtFldListenerIDToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(JPnlAddPlaylistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSongIDToBeAdded)
                    .addComponent(txtFldSongIDToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(lblSucAddPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lblInstructionsAddPlaylistBtn)
                .addGap(18, 18, 18)
                .addComponent(btnAddPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        getContentPane().add(JPnlAddPlaylists, "card11");

        JPnlAddSongs.setBackground(new java.awt.Color(0, 0, 204));

        lblLogoAddSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        lblArtistIDToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblArtistIDToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblArtistIDToBeAdded.setText("ArtistID:");

        lblSongNameToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSongNameToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblSongNameToBeAdded.setText("SongName:");

        lblReleaseDateToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblReleaseDateToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblReleaseDateToBeAdded.setText("ReleaseDate:");

        lblInstructionsHeaderAddSong.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lblInstructionsHeaderAddSong.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsHeaderAddSong.setText("Instructions:");

        lblInstructionsAddSong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblInstructionsAddSong.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsAddSong.setText("Enter valid variables to create your own song");

        lblAlbumToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAlbumToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblAlbumToBeAdded.setText("Album:");

        lblGenreToBeAddedSong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGenreToBeAddedSong.setForeground(new java.awt.Color(255, 255, 255));
        lblGenreToBeAddedSong.setText("Genre:");

        txtFldArtistIDToBeAdded.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldArtistIDToBeAddedActionPerformed(evt);
            }
        });

        lblSucAddSong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSucAddSong.setForeground(new java.awt.Color(0, 255, 0));

        btnAddSong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddSong.setText("ADD");
        btnAddSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSongActionPerformed(evt);
            }
        });

        lblAddSongInstructions.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAddSongInstructions.setForeground(new java.awt.Color(255, 255, 255));
        lblAddSongInstructions.setText("Click button when done adding variables to the textFields");

        lblDurationToBeAdded.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDurationToBeAdded.setForeground(new java.awt.Color(255, 255, 255));
        lblDurationToBeAdded.setText("Duration: (eg: 3mins)");

        javax.swing.GroupLayout JPnlAddSongsLayout = new javax.swing.GroupLayout(JPnlAddSongs);
        JPnlAddSongs.setLayout(JPnlAddSongsLayout);
        JPnlAddSongsLayout.setHorizontalGroup(
            JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlAddSongsLayout.createSequentialGroup()
                .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlAddSongsLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInstructionsHeaderAddSong)
                            .addComponent(lblInstructionsAddSong))
                        .addGap(144, 144, 144)
                        .addComponent(lblLogoAddSong)
                        .addGap(0, 377, Short.MAX_VALUE))
                    .addGroup(JPnlAddSongsLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSongNameToBeAdded)
                            .addComponent(lblArtistIDToBeAdded)
                            .addComponent(lblReleaseDateToBeAdded)
                            .addComponent(lblGenreToBeAddedSong)
                            .addComponent(lblDurationToBeAdded)
                            .addComponent(lblAlbumToBeAdded))
                        .addGap(41, 41, 41)
                        .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFldDurationToBeAdded, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(txtFldArtistIDToBeAdded)
                            .addComponent(txtFldSongNameToBeAdded)
                            .addComponent(txtFldGenreToBeAddedSong)
                            .addComponent(txtFldAlbumToBeAdded)
                            .addComponent(songReleaseDateToBeAdded, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSucAddSong, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddSongsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddSongsLayout.createSequentialGroup()
                        .addComponent(btnAddSong, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlAddSongsLayout.createSequentialGroup()
                        .addComponent(lblAddSongInstructions)
                        .addContainerGap())))
        );
        JPnlAddSongsLayout.setVerticalGroup(
            JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlAddSongsLayout.createSequentialGroup()
                .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlAddSongsLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(lblInstructionsHeaderAddSong)
                        .addGap(18, 18, 18)
                        .addComponent(lblInstructionsAddSong))
                    .addGroup(JPnlAddSongsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogoAddSong)))
                .addGap(38, 38, 38)
                .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldArtistIDToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblArtistIDToBeAdded)
                    .addComponent(lblSucAddSong, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldSongNameToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSongNameToBeAdded))
                .addGap(17, 17, 17)
                .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReleaseDateToBeAdded)
                    .addComponent(songReleaseDateToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldGenreToBeAddedSong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenreToBeAddedSong))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldDurationToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDurationToBeAdded))
                .addGap(18, 18, 18)
                .addGroup(JPnlAddSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldAlbumToBeAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlbumToBeAdded))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(lblAddSongInstructions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddSong, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        getContentPane().add(JPnlAddSongs, "card12");

        JPnlArtistSearch.setBackground(new java.awt.Color(0, 0, 204));

        lblSearchArtistLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        tblSearchArtist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "artist", "grammyNominated", "latestAlbum", "genre", "albumSales", "upcomingTours", "phoneNum", "website", "streamingPlatforms", "mostStreamedSong", "otherOccupation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JScrlSearchArtist.setViewportView(tblSearchArtist);
        if (tblSearchArtist.getColumnModel().getColumnCount() > 0) {
            tblSearchArtist.getColumnModel().getColumn(5).setHeaderValue("upcomingTours");
            tblSearchArtist.getColumnModel().getColumn(6).setHeaderValue("phoneNum");
            tblSearchArtist.getColumnModel().getColumn(7).setHeaderValue("website");
            tblSearchArtist.getColumnModel().getColumn(8).setHeaderValue("streamingPlatforms");
            tblSearchArtist.getColumnModel().getColumn(9).setHeaderValue("mostStreamedSong");
            tblSearchArtist.getColumnModel().getColumn(10).setHeaderValue("otherOccupation");
        }

        lblSearchArtist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSearchArtist.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchArtist.setText("Enter the artist's name you would like to search for:");

        btnSearchArtist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearchArtist.setText("SEARCH");
        btnSearchArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchArtistActionPerformed(evt);
            }
        });

        btnBackToArtistTblSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBackToArtistTblSearch.setText("BACK");
        btnBackToArtistTblSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToArtistTblSearchActionPerformed(evt);
            }
        });

        lblInstructionsSearchArtist.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lblInstructionsSearchArtist.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsSearchArtist.setText("Instructions:");

        lblActualInstructionsArtistSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblActualInstructionsArtistSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblActualInstructionsArtistSearch.setText("Enter an artist to search for (enter their name)");

        lblSearchArtistBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSearchArtistBtn.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchArtistBtn.setText("When done click this button to search for the artist you entered");

        lblBackToArtistTblSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBackToArtistTblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToArtistTblSearch.setText("Back to artist table");

        lblFoundArtist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblFoundArtist.setForeground(new java.awt.Color(0, 255, 0));

        lblNotFoundArtist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNotFoundArtist.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout JPnlArtistSearchLayout = new javax.swing.GroupLayout(JPnlArtistSearch);
        JPnlArtistSearch.setLayout(JPnlArtistSearchLayout);
        JPnlArtistSearchLayout.setHorizontalGroup(
            JPnlArtistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlArtistSearchLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblSearchArtist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFldArtistToSearchFor, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlArtistSearchLayout.createSequentialGroup()
                .addGroup(JPnlArtistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlArtistSearchLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(lblActualInstructionsArtistSearch))
                    .addGroup(JPnlArtistSearchLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(lblInstructionsSearchArtist)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 423, Short.MAX_VALUE)
                .addComponent(lblSearchArtistLogo)
                .addGap(148, 148, 148))
            .addGroup(JPnlArtistSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JScrlSearchArtist)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlArtistSearchLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(JPnlArtistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlArtistSearchLayout.createSequentialGroup()
                        .addComponent(lblSearchArtistBtn)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlArtistSearchLayout.createSequentialGroup()
                        .addGroup(JPnlArtistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblBackToArtistTblSearch)
                            .addGroup(JPnlArtistSearchLayout.createSequentialGroup()
                                .addGroup(JPnlArtistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblFoundArtist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblNotFoundArtist, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(JPnlArtistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBackToArtistTblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSearchArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(165, 165, 165))))
        );
        JPnlArtistSearchLayout.setVerticalGroup(
            JPnlArtistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlArtistSearchLayout.createSequentialGroup()
                .addGroup(JPnlArtistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlArtistSearchLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSearchArtistLogo))
                    .addGroup(JPnlArtistSearchLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lblInstructionsSearchArtist)
                        .addGap(18, 18, 18)
                        .addComponent(lblActualInstructionsArtistSearch)))
                .addGap(18, 18, 18)
                .addComponent(JScrlSearchArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(JPnlArtistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchArtist)
                    .addComponent(txtFldArtistToSearchFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(lblSearchArtistBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPnlArtistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlArtistSearchLayout.createSequentialGroup()
                        .addComponent(btnSearchArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(lblBackToArtistTblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBackToArtistTblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(JPnlArtistSearchLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lblFoundArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblNotFoundArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getContentPane().add(JPnlArtistSearch, "card13");

        JPnlListenerSearch.setBackground(new java.awt.Color(0, 0, 204));

        lblSearchListenerLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        tblSearchListener.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "listener", "name", "surname", "prefferedGenre", "username"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JScrlSearchListener.setViewportView(tblSearchListener);

        lblSearchListener.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSearchListener.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchListener.setText("Enter a listener to for:");

        txtFldListenerToSearchFor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldListenerToSearchForActionPerformed(evt);
            }
        });

        btnSearchListener.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearchListener.setText("SEARCH");
        btnSearchListener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchListenerActionPerformed(evt);
            }
        });

        btnBackToListenerTblSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBackToListenerTblSearch.setText("BACK");
        btnBackToListenerTblSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToListenerTblSearchActionPerformed(evt);
            }
        });

        lblInstructionsSearchListener.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lblInstructionsSearchListener.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsSearchListener.setText("Instructions:");

        lblActualInstructionsListenerSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblActualInstructionsListenerSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblActualInstructionsListenerSearch.setText("Enter a listener to search for (enter their listener name)");

        lblSearchListenerBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSearchListenerBtn.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchListenerBtn.setText("When done entering click this button to search for the listener");

        lblBackToListenerTblSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBackToListenerTblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToListenerTblSearch.setText("Back to the listeners table");

        lblFoundListener.setBackground(new java.awt.Color(255, 255, 255));
        lblFoundListener.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFoundListener.setForeground(new java.awt.Color(0, 255, 0));

        lblListenerNotFound.setBackground(new java.awt.Color(255, 255, 255));
        lblListenerNotFound.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblListenerNotFound.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout JPnlListenerSearchLayout = new javax.swing.GroupLayout(JPnlListenerSearch);
        JPnlListenerSearch.setLayout(JPnlListenerSearchLayout);
        JPnlListenerSearchLayout.setHorizontalGroup(
            JPnlListenerSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlListenerSearchLayout.createSequentialGroup()
                .addGroup(JPnlListenerSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(lblActualInstructionsListenerSearch))
                    .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(lblInstructionsSearchListener)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 364, Short.MAX_VALUE)
                .addComponent(lblSearchListenerLogo)
                .addGap(148, 148, 148))
            .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JScrlSearchListener)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlListenerSearchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBackToListenerTblSearch)
                .addGap(125, 125, 125))
            .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(JPnlListenerSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                        .addComponent(lblSearchListener)
                        .addGap(19, 19, 19))
                    .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                        .addGroup(JPnlListenerSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFoundListener, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(lblListenerNotFound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(173, 173, 173)))
                .addGroup(JPnlListenerSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                        .addComponent(txtFldListenerToSearchFor, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlListenerSearchLayout.createSequentialGroup()
                        .addGroup(JPnlListenerSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBackToListenerTblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchListener, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(147, 147, 147))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlListenerSearchLayout.createSequentialGroup()
                        .addComponent(lblSearchListenerBtn)
                        .addContainerGap())))
        );
        JPnlListenerSearchLayout.setVerticalGroup(
            JPnlListenerSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                .addGroup(JPnlListenerSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSearchListenerLogo))
                    .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lblInstructionsSearchListener)
                        .addGap(18, 18, 18)
                        .addComponent(lblActualInstructionsListenerSearch)))
                .addGap(18, 18, 18)
                .addComponent(JScrlSearchListener, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(JPnlListenerSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchListener)
                    .addComponent(txtFldListenerToSearchFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(lblSearchListenerBtn)
                .addGap(18, 18, 18)
                .addGroup(JPnlListenerSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                        .addComponent(btnSearchListener, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBackToListenerTblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBackToListenerTblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(JPnlListenerSearchLayout.createSequentialGroup()
                        .addComponent(lblFoundListener, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblListenerNotFound, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110))))
        );

        getContentPane().add(JPnlListenerSearch, "card13");

        JPnlPlaylistSearch.setBackground(new java.awt.Color(0, 0, 204));

        lblSearchPlaylistLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        tblSearchPlaylist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "listenerID", "songID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JScrlSearchPlaylist.setViewportView(tblSearchPlaylist);

        lblSearchPlaylist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSearchPlaylist.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchPlaylist.setText("Enter listenerID to search for a playlist:");

        btnSearchPlaylist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearchPlaylist.setText("SEARCH");
        btnSearchPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPlaylistActionPerformed(evt);
            }
        });

        btnBackToPlaylistTblSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBackToPlaylistTblSearch.setText("BACK");
        btnBackToPlaylistTblSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToPlaylistTblSearchActionPerformed(evt);
            }
        });

        lblInstructionsSearchPlaylist.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lblInstructionsSearchPlaylist.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsSearchPlaylist.setText("Instructions:");

        lblActualInstructionsPlaylistSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblActualInstructionsPlaylistSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblActualInstructionsPlaylistSearch.setText("Enter a playlist to search for (enter listenerID)");

        btnSearchInstructions.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearchInstructions.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchInstructions.setText("When entered into textField press this search button");

        lblBackToPlaylistTblSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBackToPlaylistTblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToPlaylistTblSearch.setText("Back to Playlist table");

        lblFoundPlaylist.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFoundPlaylist.setForeground(new java.awt.Color(0, 255, 0));

        lblNotFoundPlaylist.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNotFoundPlaylist.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout JPnlPlaylistSearchLayout = new javax.swing.GroupLayout(JPnlPlaylistSearch);
        JPnlPlaylistSearch.setLayout(JPnlPlaylistSearchLayout);
        JPnlPlaylistSearchLayout.setHorizontalGroup(
            JPnlPlaylistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlPlaylistSearchLayout.createSequentialGroup()
                .addGroup(JPnlPlaylistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlPlaylistSearchLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(lblActualInstructionsPlaylistSearch))
                    .addGroup(JPnlPlaylistSearchLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(lblInstructionsSearchPlaylist)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSearchPlaylistLogo)
                .addGap(148, 148, 148))
            .addGroup(JPnlPlaylistSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JScrlSearchPlaylist)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlPlaylistSearchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPnlPlaylistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlPlaylistSearchLayout.createSequentialGroup()
                        .addComponent(btnSearchInstructions)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlPlaylistSearchLayout.createSequentialGroup()
                        .addComponent(lblBackToPlaylistTblSearch)
                        .addGap(108, 108, 108))))
            .addGroup(JPnlPlaylistSearchLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(JPnlPlaylistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlPlaylistSearchLayout.createSequentialGroup()
                        .addComponent(lblSearchPlaylist)
                        .addGap(18, 18, 18)
                        .addComponent(txtFldPlaylistToSearchFor, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(589, Short.MAX_VALUE))
                    .addGroup(JPnlPlaylistSearchLayout.createSequentialGroup()
                        .addGroup(JPnlPlaylistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblNotFoundPlaylist, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(lblFoundPlaylist, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(JPnlPlaylistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBackToPlaylistTblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(118, 118, 118))))
        );
        JPnlPlaylistSearchLayout.setVerticalGroup(
            JPnlPlaylistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlPlaylistSearchLayout.createSequentialGroup()
                .addGroup(JPnlPlaylistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlPlaylistSearchLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSearchPlaylistLogo))
                    .addGroup(JPnlPlaylistSearchLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lblInstructionsSearchPlaylist)
                        .addGap(18, 18, 18)
                        .addComponent(lblActualInstructionsPlaylistSearch)))
                .addGap(18, 18, 18)
                .addComponent(JScrlSearchPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(JPnlPlaylistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchPlaylist)
                    .addComponent(txtFldPlaylistToSearchFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnSearchInstructions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPnlPlaylistSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSearchPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFoundPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblNotFoundPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBackToPlaylistTblSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBackToPlaylistTblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(JPnlPlaylistSearch, "card13");

        JPnlSongSearch.setBackground(new java.awt.Color(0, 0, 204));

        lblSearchSongLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/AfroLogoEdit.jpg"))); // NOI18N

        tblSearchSong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "artistID", "songName", "releaseDate", "genre", "duration", "album"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JScrlSearchSong.setViewportView(tblSearchSong);

        lblSearchSong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSearchSong.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchSong.setText("Enter a songID to search for an artist:");

        btnSearchSong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearchSong.setText("SEARCH");
        btnSearchSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSongActionPerformed(evt);
            }
        });

        btnBackToSongTblSearch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBackToSongTblSearch.setText("BACK");
        btnBackToSongTblSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToSongTblSearchActionPerformed(evt);
            }
        });

        lblInstructionsSearchSong.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lblInstructionsSearchSong.setForeground(new java.awt.Color(255, 255, 255));
        lblInstructionsSearchSong.setText("Instructions:");

        lblActualInstructionsSongSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblActualInstructionsSongSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblActualInstructionsSongSearch.setText("Enter a song to search for (enter their artistID)");

        lblSearchArtistIDBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSearchArtistIDBtn.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchArtistIDBtn.setText("When done entering a artistID click this button to search for that song");

        lblBackToSongtblSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBackToSongtblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToSongtblSearch.setText("Back to songs table");

        lblFoundSong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFoundSong.setForeground(new java.awt.Color(0, 255, 0));

        lblNotFoundSong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNotFoundSong.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout JPnlSongSearchLayout = new javax.swing.GroupLayout(JPnlSongSearch);
        JPnlSongSearch.setLayout(JPnlSongSearchLayout);
        JPnlSongSearchLayout.setHorizontalGroup(
            JPnlSongSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlSongSearchLayout.createSequentialGroup()
                .addGroup(JPnlSongSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(lblActualInstructionsSongSearch))
                    .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(lblInstructionsSearchSong)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSearchSongLogo)
                .addGap(148, 148, 148))
            .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JScrlSearchSong)
                .addContainerGap())
            .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(JPnlSongSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                        .addGroup(JPnlSongSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 794, Short.MAX_VALUE)
                                .addComponent(btnBackToSongTblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlSongSearchLayout.createSequentialGroup()
                                .addComponent(lblFoundSong, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSearchSong, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(166, 166, 166))
                    .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                        .addGroup(JPnlSongSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNotFoundSong, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                                .addComponent(lblSearchSong)
                                .addGap(18, 18, 18)
                                .addComponent(txtFldSongToSearchFor, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlSongSearchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPnlSongSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlSongSearchLayout.createSequentialGroup()
                        .addComponent(lblSearchArtistIDBtn)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPnlSongSearchLayout.createSequentialGroup()
                        .addComponent(lblBackToSongtblSearch)
                        .addGap(158, 158, 158))))
        );
        JPnlSongSearchLayout.setVerticalGroup(
            JPnlSongSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                .addGroup(JPnlSongSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSearchSongLogo))
                    .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lblInstructionsSearchSong)
                        .addGap(18, 18, 18)
                        .addComponent(lblActualInstructionsSongSearch)))
                .addGap(18, 18, 18)
                .addComponent(JScrlSearchSong, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(JPnlSongSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchSong)
                    .addComponent(txtFldSongToSearchFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblSearchArtistIDBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPnlSongSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearchSong, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPnlSongSearchLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblFoundSong, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNotFoundSong, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(lblBackToSongtblSearch)
                .addGap(18, 18, 18)
                .addComponent(btnBackToSongTblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(JPnlSongSearch, "card13");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFldAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldAddUserActionPerformed
        //storing username entered by the user
        String newUsername = txtFldAddUser.getText();
    }//GEN-LAST:event_txtFldAddUserActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        //this code is made for when the user enters their username and password so that it csn be checked
        
        //creating variables to be store username and password entered by the user
        boolean username = userM.loginDets("" + txtFldUsername.getText());
        boolean password = userM.loginDetsPass(txtFldPassword.getText());
        
        //if everything is correct it will take the user to the main menu panel
        if(username == true || password == true)
        {
            //checking is user has entered an variables
            if(txtFldUsername.getText().equals("") || txtFldPassword.getText().equals(""))
            {
                JOptionPane.showInputDialog(null, "Error: You have not entered a username or password");
                lblErrorMessage.setText("Error: Nothing entered, please enter username and password");
            }
            else
            {
                //setting textfields blank since username and password are correct
                txtFldUsername.setText("");
                txtFldPassword.setText("");
                //taking user to main menu
                JPnlLogin.setVisible(false);
                JPnlMainMenu.setVisible(true);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "You have entered an incorrect Username or password");
            lblErrorMessage.setText("Error: Incorrect username or password");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        //when user wants to add their detials in text file this button will take them to add user panel
        JPnlAddUser.setVisible(true);
        JPnlLogin.setVisible(false);
        //making textFields empty for the next time they enter into the textField
        txtFldUsername.setText("");
        txtFldPassword.setText("");
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        //when user presses this button there will be a document to help them understand my GUI
        try 
        {
            //creating variable to extract and open docx
            Desktop deskTop = Desktop.getDesktop();
            //if statement to check if deskop is compatible with document thats about to be loaded
            if(deskTop.isSupported(Desktop.Action.OPEN)) 
            {
                //path to word help document
                deskTop.open(new File("C:\\Users\\Bapi\\Documents\\NetBeansProjects\\MusicApplication\\Help Document for user\\IT HELP document 2020.docx"));
            } 
            else 
            {
                //telling user the file couldn't be found 
                System.out.println("Error: counldn't find file - check path");
            }
        } 
        catch (IOException d) 
        {
            //message saying file couldn't be opened
            JOptionPane.showMessageDialog(null, "Coudln't open/find file - check path");
            System.out.println(d);
        }
    }//GEN-LAST:event_btnHelpActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //try and catch statement for dangerous code 
        try
        {
            //creating printwriter variable to edit the textfile to add the user that needs to be added
            PrintWriter outFil = new PrintWriter(new FileWriter("Login.txt", true));
            if(txtFldAddUser.getText()instanceof String)
            {
                //checking if user actually entered values into the textfields
                if(txtFldAddUser.getText().equals("") || txtFldAddPassword.getText().equals(""))
                {
                    //dislaying error message
                    JOptionPane.showMessageDialog(null, "You have not entered a username or password");
                }
                else
                {
                    //getting text from the text file and adding the username and password to be added to the textfile
                    outFil.println(txtFldAddUser.getText() + "%" + txtFldAddPassword.getText());
                    outFil.close();
                    //output message to tell the user their usrname and password have been sucessfully added
                    lblAddUserSuc.setText("User successfully added");    
                }
                //code to add users to txt file
                //userM.populateUserArr(txtFldAddUser.getText(), txtFldAddPassword.getText());
            }
            else
            {
                //JOptionPane to show user message they haven't used the correct format to add a user
                JOptionPane.showInputDialog("Error: Enter the correct format for username and password");
            }
        }
        catch(IOException x)
        {
            //message to display that the file hasn't been found or there ws an error adding username and password
            System.out.println("Error: File not found or error opening path - check path");
        }
        
        JPnlAddUser.setVisible(false);
        JPnlMainMenu.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //taking user to pervious page (login page to login)
        JPnlAddUser.setVisible(false);
        JPnlLogin.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnArtistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArtistsActionPerformed
        //taking user to artist table menu
        JPnlMainMenu.setVisible(false);
        JPnlArtists.setVisible(true);
    }//GEN-LAST:event_btnArtistsActionPerformed

    private void btnListenersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListenersActionPerformed
        //taking user to artist table menu
        JPnlMainMenu.setVisible(false);
        JPnlListeners.setVisible(true);
    }//GEN-LAST:event_btnListenersActionPerformed

    private void btnPlaylistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaylistsActionPerformed
        //taking user to artist table menu
        JPnlMainMenu.setVisible(false);
        JPnlPlaylists.setVisible(true);
    }//GEN-LAST:event_btnPlaylistsActionPerformed

    private void btnSongsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSongsActionPerformed
        //taking user to artist table menu
        JPnlMainMenu.setVisible(false);
        JPnlSongs.setVisible(true);
    }//GEN-LAST:event_btnSongsActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        //closing program as user requested
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnBackToMenuArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToMenuArtistActionPerformed
        //taking user back to main menu using "BACK" button
        JPnlArtists.setVisible(false);
        JPnlMainMenu.setVisible(true);
    }//GEN-LAST:event_btnBackToMenuArtistActionPerformed

    private void btnAddArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddArtistActionPerformed
        //taking user to new panel where they'll be able to create an artist object
        JPnlArtists.setVisible(false);
        JPnlAddArtists.setVisible(true);
    }//GEN-LAST:event_btnAddArtistActionPerformed

    private void btnDeleteArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteArtistActionPerformed
        //creating int array variable for the amount of rows that need to be deleted
        int[] tblRows = tblArtists.getSelectedRows();
        //getting the amount of rows that need to be deleted and repeating action using for loop
        for(int i = tblRows.length; i>=0; i--)
        {
            String rowCode = (String)(modelArtist.getValueAt(tblRows[i], 0));
            //using delete method in manager class
            System.out.println(artM.deleteArtist(rowCode));
            //reloading a refreshed/updated versions of the tables
            clearArtistModel();
            loadArtists();
        }
    }//GEN-LAST:event_btnDeleteArtistActionPerformed

    private void btnBackToMenuListenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToMenuListenerActionPerformed
        //taking user back to main menu using "BACK" button
        JPnlListeners.setVisible(false);
        JPnlMainMenu.setVisible(true);
    }//GEN-LAST:event_btnBackToMenuListenerActionPerformed

    private void btnCreateListenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateListenerActionPerformed
        //taking user to new panel where they'll be able to create an playlist object
        JPnlListeners.setVisible(false);
        JPnlAddListeners.setVisible(true);
    }//GEN-LAST:event_btnCreateListenerActionPerformed

    private void btnDeleteListenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteListenerActionPerformed
        //creating int array variable for the amount of rows that need to be deleted
        int[] tblRows = tblListeners.getSelectedRows();
        //getting the amount of rows that need to be deleted and repeating action using for loop
        for(int i = tblRows.length; i>=0; i--)
        {
            String rowCode = (String)(modelListener.getValueAt(tblRows[i], 0));
            //using delete method in manager class
            System.out.println(listM.deleteListener(rowCode));
            //loading the new table after data got delete
            clearListenersModel();
            loadListeners();
        }
    }//GEN-LAST:event_btnDeleteListenerActionPerformed

    private void btnBackToMenuPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToMenuPlaylistActionPerformed
        //taking user back to main menu using "BACK" button
        JPnlPlaylists.setVisible(false);
        JPnlMainMenu.setVisible(true);
    }//GEN-LAST:event_btnBackToMenuPlaylistActionPerformed

    private void btnCreatePlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePlaylistActionPerformed
        //taking user to new panel where they'll be able to create an listener object
        JPnlPlaylists.setVisible(false);
        JPnlAddPlaylists.setVisible(true);
    }//GEN-LAST:event_btnCreatePlaylistActionPerformed

    private void btnDeletePlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePlaylistActionPerformed
        //creating int array variable for the amount of rows that need to be deleted
        int[] tblRows = tblPlaylists.getSelectedRows();
        //getting the amount of rows that need to be deleted and repeating action using for loop
        for(int i = tblRows.length; i>=0; i--)
        {
            String rowCode = (String)(modelPlaylists.getValueAt(tblRows[i], 0));
            //using delete method in manager class
            System.out.println(playM.deletePlaylist(rowCode));
            //loading new data in the table after data got deleted
            clearPlaylistsModel();
            loadPlaylist();
        }
    }//GEN-LAST:event_btnDeletePlaylistActionPerformed

    private void btnBackToMenuSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToMenuSongActionPerformed
        //taking user back to main menu as requested
        JPnlSongs.setVisible(false);
        JPnlMainMenu.setVisible(true);
    }//GEN-LAST:event_btnBackToMenuSongActionPerformed

    private void btnCreateSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateSongActionPerformed
        //taking user to panel where they can create their own song
        JPnlSongs.setVisible(false);
        JPnlAddSongs.setVisible(true);
    }//GEN-LAST:event_btnCreateSongActionPerformed

    private void btnDeleteSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSongActionPerformed
        //creating int array variable for the amount of rows that need to be deleted
        int[] tblRows = tblSongs.getSelectedRows();
        //getting the amount of rows that need to be deleted and repeating action using for loop
        for(int i = tblRows.length; i>=0; i--)
        {
            int rowCode = (int)(modelSong.getValueAt(tblRows[i], 0));
            //using delete method in manager class
            System.out.println(songM.deleteSong(rowCode));
            //loading new table data after a row got deleted
            clearSongModel();
            loadSongs();
        }
    }//GEN-LAST:event_btnDeleteSongActionPerformed

    private void btnAddNewArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewArtistActionPerformed
        //refreshing table so it can sync the values that need to be added
        refresh = true;
        
        //creating variables for what user has entered
        int artistID = (int)(Integer.parseInt(txtFldArtistIDToBeAdded.getText()));
        String artistName = txtFldArtistToBeAdded.getText();
        boolean grammyNominated = chkBoxGrammyNominatedToBeAdded.isSelected();
        String latestAlbum = txtFldLatestAlbumToBeAdded.getText();
        String genre = txtFldGenreToBeAdded.getText();
        int albumSales = (int)(Integer.parseInt(txtFldAlbumSaleToBeAdded.getText()));
        String upcomingTours = txtFldUpcomingTourToBeAdded.getText();
        int phoneNum = (int)(Integer.parseInt(txtFldPhoneNumToBeAdded.getText()));
        String website = txtFldWebsiteToBeAdded.getText();
        String streamingPlatform = txtFldStreamingToBeAdded.getText();
        String mostStreamedSong = txtFldMostStreamedToBeAdded.getText();
        String otherOccupation = txtFldOtherOccToBeAdded.getText();
        
        //using object class to create a new artist object using user variables
        Artist a = new Artist(artistID, artistName, grammyNominated, latestAlbum, genre, albumSales, 
        upcomingTours, phoneNum, website, streamingPlatform, mostStreamedSong, otherOccupation);
        
        //using manager class method to add an artist
        artM.addArtist(a);
        
        //updating table using clear and load methods
        clearArtistModel();
        loadArtists();
        
        //telling user the artist was successfully added
        lblSucAddArtist.setText("Arist successfully artist");
        
        //clearing text field so user can add another artist if they wish to
        txtFldArtistToBeAdded.setText("");
        chkBoxGrammyNominatedToBeAdded.setSelected(false);
        txtFldLatestAlbumToBeAdded.setText("");
        txtFldGenreToBeAdded.setText("");
        txtFldAlbumSaleToBeAdded.setText("");
        txtFldUpcomingTourToBeAdded.setText("");
        txtFldPhoneNumToBeAdded.setText("");
        txtFldWebsiteToBeAdded.setText("");
        txtFldStreamingToBeAdded.setText("");
        txtFldOtherOccToBeAdded.setText("");
        
        //taking user to refreshed table with their added artist
        JPnlAddArtists.setVisible(false);
        JPnlArtists.setVisible(true);
        
        //stopping the table from refreshing 
        refresh = false;
    }//GEN-LAST:event_btnAddNewArtistActionPerformed

    private void btnAddListenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddListenerActionPerformed
        //refreshing table so it can sync the values that need to be added
        refresh = true;
        
        //creating variables for what user has entered
        int listenerID = (int)(Integer.parseInt(txtFldListenerIDToBeAddedL.getText()));
        String listener = txtFldListenerToBeAdded.getText();
        String name = txtFldNameToBeAdded.getText();
        String surname = txtFldSurnameToBeAdded.getText();
        String prefferedGenre = txtFldPrefferedGenreToBeAdded.getText();
        String username = txtFldUsernameToBeAdded.getText();
        
        //using object class to create a new listener object using user variables
        Listener l = new Listener(listenerID, listener, name, surname, prefferedGenre, username);
        
        //using manager class method to add an listener
        listM.addListeners(l);
        
        //updating table using clear and load methods
        clearListenersModel();
        loadListeners();
        
        //telling user the listener was successfully added
        lblSucAddListener.setText("Listener successfully artist");
        
        //clearing text field so user can add another listener if they wish to
        txtFldListenerToBeAdded.setText("");
        txtFldNameToBeAdded.setText("");
        txtFldPrefferedGenreToBeAdded.setText("");
        txtFldUsernameToBeAdded.setText("");
        
        //taking user to refreshed table with their added listener
        JPnlAddListeners.setVisible(false);
        JPnlListeners.setVisible(true);
        
        //stopping the table from refreshing 
        refresh = false;
    }//GEN-LAST:event_btnAddListenerActionPerformed

    private void btnAddPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPlaylistActionPerformed
        //creating variables for what user has entered
        String listenerID = txtFldListenerToBeAdded.getText();
        int songID = (int)(Integer.parseInt(txtFldNameToBeAdded.getText()));
        
        //using object class to create a new playlist object using user variables
        Playlist p = new Playlist(listenerID, songID);
        
        //using manager class method to add an playlist
        playM.addPlaylist(p);
        
        //updating table using clear and load methods
        clearPlaylistsModel();
        loadPlaylist();
        
        //telling user the playlist was successfully added
        lblSucAddPlaylist.setText("Playlist successfully artist");
        
        //clearing text field so user can add another playlist if they wish to
        txtFldListenerIDToBeAdded.setText("");
        txtFldSongIDToBeAdded.setText("");
        
        //taking user to refreshed table with their added playlist
        JPnlAddPlaylists.setVisible(false);
        JPnlPlaylists.setVisible(true);
        
        //stopping the table from refreshing 
        refresh = false;
    }//GEN-LAST:event_btnAddPlaylistActionPerformed

    private void btnAddSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSongActionPerformed
        //refreshing table so it can sync the values that need to be added
        refresh = true;
        
        //creating variables for what user has entered
        int artistID = (int)(Integer.parseInt(txtFldArtistIDToBeAdded.getText()));
        String songName = txtFldSongNameToBeAdded.getText();
        Date releaseDate = songReleaseDateToBeAdded.getDate();
        String genre = txtFldGenreToBeAdded.getText();
        String duration = txtFldDurationToBeAdded.getText();
        String album = txtFldAlbumToBeAdded.getText();
        
        //using object class to create a new song object using user variables
        Song s = new Song(artistID, songName, releaseDate, genre, duration, album);
        
        //using manager class method to add an song
        songM.addSongs(s);
        
        //updating table using clear and load methods
        clearSongModel();
        loadSongs();
        
        //telling user the songs successfully added
        lblSucAddPlaylist.setText("Song successfully artist");
        
        //clearing text field so user can add another song if they wish to
        txtFldArtistIDToBeAdded.setText("");
        txtFldSongNameToBeAdded.setText("");
        songReleaseDateToBeAdded.setDate(null);
        txtFldGenreToBeAdded.setText("");
        txtFldDurationToBeAdded.setText("");
        txtFldAlbumToBeAdded.setText("");
        
        //taking user to new table with their added song
        JPnlAddSongs.setVisible(false);
        JPnlSongs.setVisible(true);
        
        //stopping the table from refreshing 
        refresh = false;
    }//GEN-LAST:event_btnAddSongActionPerformed

    private void btnBackToArtistTblSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToArtistTblSearchActionPerformed
        //taking user back to main menu as requested
        JPnlArtistSearch.setVisible(false);
        JPnlMainMenu.setVisible(true);
    }//GEN-LAST:event_btnBackToArtistTblSearchActionPerformed

    private void btnSearchArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchArtistActionPerformed
        //this button is made for the user so they can search for a listener
        int confirmArtist = JOptionPane.showConfirmDialog(null, "Note: Are you sure you want to search for this Artist");
        //if confirm equals 0 (true) a method from the artist manager class will be used to search for the artist
        if(confirmArtist == 0)
        {
            //calling searchArtist method
            artM.searchArtist(txtFldArtistToSearchFor.getText());
        }
        else
        {
            //error message telling the user search couldn't be completed
            lblNotFoundArtist.setText("Error: Couldn't find artist being searched for - check spelling");
        }
    }//GEN-LAST:event_btnSearchArtistActionPerformed

    private void btnSearchListenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchListenerActionPerformed
        //this button is made for the user so they can search for a listener
        int confirmList = JOptionPane.showConfirmDialog(null, "Note: Are you sure you want to search for this Listener");
        //if confirm equals 0 (true) a method from the listener manager class will be used to search for the listener
        if(confirmList == 0)
        {
            //calling searchListener method
            listM.searchListener(txtFldListenerToSearchFor.getText());
        }
        else
        {
            //displaying error message telling user search couldn't be completed
            lblListenerNotFound.setText("Error: Couldn't find listener being searched for - check spelling");
        }
    }//GEN-LAST:event_btnSearchListenerActionPerformed

    private void btnBackToListenerTblSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToListenerTblSearchActionPerformed
        //taking user back to artist table
        JPnlListenerSearch.setVisible(false);
        JPnlListeners.setVisible(true);
    }//GEN-LAST:event_btnBackToListenerTblSearchActionPerformed

    private void btnSearchPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPlaylistActionPerformed
        //this button is made so that the user can search for whatever playlist they want
        int confirmPlay = JOptionPane.showConfirmDialog(null, "Note: Are you sure you want to search for this Playlist");
        //if confirm equals 0 (true) a method from the playlist manager class will be used to search for the playlist
        if(confirmPlay == 0)
        {
            //calling searchPlay method
            playM.searchPlaylist(txtFldPlaylistToSearchFor.getText());
        }
        else
        {
            //dsiplaying error message if search couldn't be completed
            lblNotFoundPlaylist.setText("Error: Couldn't find playlist being searched for - check spelling");
        }
    }//GEN-LAST:event_btnSearchPlaylistActionPerformed

    private void btnBackToPlaylistTblSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToPlaylistTblSearchActionPerformed
        //taking user back to playlist table
        JPnlPlaylistSearch.setVisible(false);
        JPnlPlaylists.setVisible(true);
    }//GEN-LAST:event_btnBackToPlaylistTblSearchActionPerformed

    private void btnSearchSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSongActionPerformed
        //this button was made for the user to search for whatever song they want
        int confirmSong = JOptionPane.showConfirmDialog(null, "Note: Are you sure you want to search for this Song");
        //if confirm equals 0 (true) a method from the song manager class will be used to search for the song
        if(confirmSong == 0)
        {
            //calling searchSong method
            songM.searchSong(Integer.parseInt(txtFldSongToSearchFor.getText()));
        }
        else
        {
            //error message telling the user search couldn't be complete
            lblNotFoundSong.setText("Error: Couldn't find song being searched for - check spelling");
        }
    }//GEN-LAST:event_btnSearchSongActionPerformed

    private void btnBackToSongTblSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToSongTblSearchActionPerformed
        //taking user back to song tbl
        JPnlSongSearch.setVisible(false);
        JPnlSongs.setVisible(true);
    }//GEN-LAST:event_btnBackToSongTblSearchActionPerformed

    private void txtFldListenerToSearchForActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldListenerToSearchForActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldListenerToSearchForActionPerformed

    private void btnSearchArtistPanelChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchArtistPanelChangeActionPerformed
        //taking user to panel where they can search for an artisy
        JPnlArtists.setVisible(false);
        JPnlArtistSearch.setVisible(true);
    }//GEN-LAST:event_btnSearchArtistPanelChangeActionPerformed

    private void btnSearchListenerChangePanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchListenerChangePanelActionPerformed
        //taking user to panel where they can search for a listener
        JPnlListeners.setVisible(false);
        JPnlListenerSearch.setVisible(true);
    }//GEN-LAST:event_btnSearchListenerChangePanelActionPerformed

    private void btnSearchPlaylistPanelChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPlaylistPanelChangeActionPerformed
        //taking user to panel where they can search for a playlist
        JPnlPlaylists.setVisible(true);
        JPnlPlaylistSearch.setVisible(false);
    }//GEN-LAST:event_btnSearchPlaylistPanelChangeActionPerformed

    private void btnSearchSongPanelChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSongPanelChangeActionPerformed
        //taking user to panel where they can search for a playlist
        JPnlSongs.setVisible(true);
        JPnlSongSearch.setVisible(false);
    }//GEN-LAST:event_btnSearchSongPanelChangeActionPerformed

    private void txtFldArtistIDToBeAddedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldArtistIDToBeAddedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldArtistIDToBeAddedActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JGUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JGUIFrame().setVisible(true);
            }
        });
    }
    
    //clicking on the table currently displayed and updating same time
    public void employArtistListener()
    {
        tblArtists.getModel().addTableModelListener (new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent tme)
            {
                //if statement so it can check if it's being updated or not
                if (refresh == true) {}
                else
                {
                    // variables to check what data we are dealing with that needs to be updated in the table being displayed
                    int row = tme.getFirstRow();
                    int artistID = Integer.parseInt(tblArtists.getValueAt(row, 0).toString());
                    String artist = tblArtists.getValueAt(row, 1).toString();
                    String grammyNominated = tblArtists.getValueAt(row,2).toString();
                    String latestAlbum = tblArtists.getValueAt(row, 3).toString();
                    String genre = tblArtists.getValueAt(row, 4).toString();
                    int albumSales = Integer.parseInt(tblArtists.getValueAt(row, 5).toString());
                    String upcomingTours = tblArtists.getValueAt(row, 6).toString();
                    int phoneNum = Integer.parseInt(tblArtists.getValueAt(row, 7).toString());
                    String website = tblArtists.getValueAt(row, 8).toString();
                    String streamingPlatform = tblArtists.getValueAt(row, 9).toString();
                    String mostStreamedSong = tblArtists.getValueAt(row, 10).toString();
                    String otherOccupation = tblArtists.getValueAt(row, 11).toString();
                    
                    //updating table with user input if they clicked something on the table
                    String sql = "UPDATE tblArtist SET " + " artist = '" + artist + "', grammyNominated = '" + grammyNominated + "', latestAlbum = '" + latestAlbum + "', genre = '"
                             + genre + "', abumSales = '" + albumSales + "', upcomingTours = '" + upcomingTours + "', phoneNum = '" + phoneNum +  "', website = '" + website +
                            "', streamingPlatform = '" + streamingPlatform + "', mostStreamedSong = '" + mostStreamedSong + "', otherOccupation = '" + otherOccupation +
                            "WHERE artistID = " + artistID + ";";
                    
                    //showing message to make sure user wants to edit the table
                    int artistCon = JOptionPane.showConfirmDialog(null, "Note: Are you sure you want to edit this table");
                    if(artistCon == 0)
                    {
                        //calling on update method to update table
                        artM.updateArtists(row, sql, refresh, sql, sql, row, sql, row, sql, sql, sql, sql);
                    }
                    //using a try and catch statement to see if theres an error updating the table
                    try
                    {
                        //running artist update query method                        
                        db.updateQuery(sql);
                        //displaying message telling user the tblArtists was updated
                        JOptionPane.showMessageDialog(null, "Success: tblArtist was successfully updated");
                    }
                    catch(SQLException se)
                    {
                        //error message telling the user tblArtists couldn't be updated
                        System.out.println("Error: unable to update tblArtists");
                    }
                }
            }
        });
    }
    
    //if user clicks on tblListener this code will run so it be edited on screen
    public void employListenerMListener()
    {
        tblListeners.getModel().addTableModelListener (new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent tme)
            {
                //if statement so user can carry on updating if the tbl is still refreshing
                if (refresh == true) {}
                else
                {
                    //making variables for the user to change on the table
                    int row = tme.getFirstRow();
                    int listenerID = Integer.parseInt(tblListeners.getValueAt(row, 0).toString());
                    String listener = tblListeners.getValueAt(row, 1).toString();
                    String name = tblListeners.getValueAt(row, 2).toString();
                    String surname = tblListeners.getValueAt(row, 3).toString();
                    String prefferedGenre = tblListeners.getValueAt(row, 4).toString();
                    String username = tblListeners.getValueAt(row, 5).toString();
                    
                    //sql code to be executed in sql to update the table
                    String sql = "UPDATE tblListeners SET "+ "listener = '" + listener + "', name = '" + name + "', surname = '" + surname + "', prefferedGenre = '" 
                            + prefferedGenre + "',  username = '" + username + "' WHERE listenerID = " + listenerID + ";";
                    
                    //showing message to confirm if user wants to edit the table
                    int listCon = JOptionPane.showConfirmDialog(null, "Note: Are sure you want to update this table");
                    if(listCon == 0)
                    {
                        //calling update method for listeners
                        listM.updateListener(row, sql, sql, sql, sql, sql);
                    }
                    
                    //try statement for dangerous code running from sql into java
                    try
                    {
                        //running listener update query method
                        db.updateQuery(sql);
                        //telling user that the tblListeners was successfully updated
                        JOptionPane.showMessageDialog(null, "Success: tblListeners has successfully updated");
                    }
                    catch (SQLException se)
                    {
                        //telling user the tblListeners couldn't be updated
                        System.out.println("Error: unable to update table");
                    }
                }
            }
        });
    }
    
    //code that will run if user clicks on tblPlaylists so they can edit the table
    public void employPlaylistListener()
    {
        tblPlaylists.getModel().addTableModelListener (new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent tme)
            {
                //if statement so that if the tbl stop refreshing it will stop updating
                if (refresh == true) {}
                else
                {
                    //variables for the user to edit as they please on the table displayed in front of them
                    int row = tme.getFirstRow();
                    String listenerID = tblPlaylists.getValueAt(row, 0).toString();
                    int songID = Integer.parseInt(tblPlaylists.getValueAt(row, 1).toString());
                    
                    
                    //sql code updating the table with whatever the user has entered 
                    String sql = "UPDATE tblPlaylists SET "+ "listenerID = '" + listenerID + "', songID = '" + songID + ";";
                    
                    //displaying message to confirm with user if they want to user the table
                    int playCon = JOptionPane.showConfirmDialog(null, "Note: Are you sure you want to update this table");
                    if(playCon == 0)
                    {
                        //calling updatePlaylist method to update the table
                        playM.updatePlaylist(sql, row, sql);
                    }
                    
                    //try statement for dangerous code running from sql back into java
                    try
                    {
                        //running update query tblPlaylists
                        db.updateQuery(sql);
                        //telling user tblPlaylists successfully updated
                        JOptionPane.showMessageDialog(null, "Success: tblPlaylist has successfully been updated");
                    }
                    catch (SQLException se)
                    {
                        //telling user tblPlaylists couldn't be updated
                        System.out.println("Error: updating tblPlaylist couldn't be executed");
                    }
                }
            }
        });
    }
    
    //code for when the user clicks on tblSongs
    public void employSongListener()
    {
        tblSongs.getModel().addTableModelListener (new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent tme)
            {
                //if statement so that when refreshing is false it will stop refreshing the table
                if (refresh == true) {}
                else
                {
                    //variables that will be edited by the user and used to update the table
                    int row = tme.getFirstRow();
                    int artistID = Integer.parseInt(tblSongs.getValueAt(row, 0).toString());
                    String songName = tblSongs.getValueAt(row, 1).toString();
                    String releaseDate = tblSongs.getValueAt(row, 2).toString();
                    String genre = tblSongs.getValueAt(row, 3).toString();
                    String duration = tblSongs.getValueAt(row, 4).toString();
                    String album = tblSongs.getValueAt(row, 5).toString();
                    
                    
                    //sql code to run in sql and update the table
                    String sql = "UPDATE  SET tblSongs"+ "artistID = '" + artistID + "', songName = '" + songName + "', releaseDate = '" + releaseDate + "', genre = '" 
                            + genre + "',  duration = '" + duration +  "', album = '" + album + "' WHERE artistID = " + artistID + ";";
                    
                    //showing user message if they are sure they want to update the table
                    int songCon = JOptionPane.showConfirmDialog(null, "Note: Are you sure you want to update this table");
                    if(songCon == 0)
                    {
                        //using updateSong method to update the table
                        //songM.updateSong(row, sql, rD, sql, sql, sql, row);
                    }
                    
                    //try statement for dangerous code running from sql back into java
                    try
                    {
                        //running songs update query method
                        db.updateQuery(sql);
                        //telling user that the tblSongs has been updated with their data
                        JOptionPane.showMessageDialog(null, "Success: tblSongs has successfully updated");
                    }
                    catch (SQLException se)
                    {
                        //telling user the command couldn't be executed
                        System.out.println("Error: couln't update tblSongs");
                    }
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPnlAddArtists;
    private javax.swing.JPanel JPnlAddListeners;
    private javax.swing.JPanel JPnlAddPlaylists;
    private javax.swing.JPanel JPnlAddSongs;
    private javax.swing.JPanel JPnlAddUser;
    private javax.swing.JPanel JPnlArtistSearch;
    private javax.swing.JPanel JPnlArtists;
    private javax.swing.JPanel JPnlListenerSearch;
    private javax.swing.JPanel JPnlListeners;
    private javax.swing.JPanel JPnlLogin;
    private javax.swing.JPanel JPnlMainMenu;
    private javax.swing.JPanel JPnlPlaylistSearch;
    private javax.swing.JPanel JPnlPlaylists;
    private javax.swing.JPanel JPnlSongSearch;
    private javax.swing.JPanel JPnlSongs;
    private javax.swing.JScrollPane JScrlArtist;
    private javax.swing.JScrollPane JScrlListeners;
    private javax.swing.JScrollPane JScrlPlaylist;
    private javax.swing.JScrollPane JScrlSearchArtist;
    private javax.swing.JScrollPane JScrlSearchListener;
    private javax.swing.JScrollPane JScrlSearchPlaylist;
    private javax.swing.JScrollPane JScrlSearchSong;
    private javax.swing.JScrollPane JScrlSong;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddArtist;
    private javax.swing.JButton btnAddListener;
    private javax.swing.JButton btnAddNewArtist;
    private javax.swing.JButton btnAddPlaylist;
    private javax.swing.JButton btnAddSong;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnArtists;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBackToArtistTblSearch;
    private javax.swing.JButton btnBackToListenerTblSearch;
    private javax.swing.JButton btnBackToMenuArtist;
    private javax.swing.JButton btnBackToMenuListener;
    private javax.swing.JButton btnBackToMenuPlaylist;
    private javax.swing.JButton btnBackToMenuSong;
    private javax.swing.JButton btnBackToPlaylistTblSearch;
    private javax.swing.JButton btnBackToSongTblSearch;
    private javax.swing.JButton btnCreateListener;
    private javax.swing.JButton btnCreatePlaylist;
    private javax.swing.JButton btnCreateSong;
    private javax.swing.JButton btnDeleteArtist;
    private javax.swing.JButton btnDeleteListener;
    private javax.swing.JButton btnDeletePlaylist;
    private javax.swing.JButton btnDeleteSong;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnListeners;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnPlaylists;
    private javax.swing.JButton btnSearchArtist;
    private javax.swing.JButton btnSearchArtistPanelChange;
    private javax.swing.JLabel btnSearchInstructions;
    private javax.swing.JButton btnSearchListener;
    private javax.swing.JButton btnSearchListenerChangePanel;
    private javax.swing.JButton btnSearchPlaylist;
    private javax.swing.JButton btnSearchPlaylistPanelChange;
    private javax.swing.JButton btnSearchSong;
    private javax.swing.JButton btnSearchSongPanelChange;
    private javax.swing.JButton btnSongs;
    private javax.swing.JCheckBox chkBoxGrammyNominatedToBeAdded;
    private javax.swing.JLabel lblActualInstructionsArtistSearch;
    private javax.swing.JLabel lblActualInstructionsListenerSearch;
    private javax.swing.JLabel lblActualInstructionsPlaylistSearch;
    private javax.swing.JLabel lblActualInstructionsSongSearch;
    private javax.swing.JLabel lblAddArtistLogo;
    private javax.swing.JLabel lblAddListener;
    private javax.swing.JLabel lblAddPassword;
    private javax.swing.JLabel lblAddSongInstructions;
    private javax.swing.JLabel lblAddUserSuc;
    private javax.swing.JLabel lblAddUsername;
    private javax.swing.JLabel lblAlbumSalesToBeAdded;
    private javax.swing.JLabel lblAlbumToBeAdded;
    private javax.swing.JLabel lblArtistIDToBeAdded;
    private javax.swing.JLabel lblArtistIDToBeAddedA;
    private javax.swing.JLabel lblArtistToBeAdded;
    private javax.swing.JLabel lblBackToArtistTblSearch;
    private javax.swing.JLabel lblBackToListenerTblSearch;
    private javax.swing.JLabel lblBackToLogin;
    private javax.swing.JLabel lblBackToMenuArtist;
    private javax.swing.JLabel lblBackToMenuListener;
    private javax.swing.JLabel lblBackToMenuPlaylist;
    private javax.swing.JLabel lblBackToMenuSong;
    private javax.swing.JLabel lblBackToPlaylistTblSearch;
    private javax.swing.JLabel lblBackToSongtblSearch;
    private javax.swing.JLabel lblCreateArtist;
    private javax.swing.JLabel lblCreateListener;
    private javax.swing.JLabel lblCreatePlaylist;
    private javax.swing.JLabel lblCreateSong;
    private javax.swing.JLabel lblDeleteArtist;
    private javax.swing.JLabel lblDeleteListener;
    private javax.swing.JLabel lblDeletePlaylist;
    private javax.swing.JLabel lblDeleteSong;
    private javax.swing.JLabel lblDurationToBeAdded;
    private javax.swing.JLabel lblErrorMessage;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblFoundArtist;
    private javax.swing.JLabel lblFoundListener;
    private javax.swing.JLabel lblFoundPlaylist;
    private javax.swing.JLabel lblFoundSong;
    private javax.swing.JLabel lblGenreToBeAdded;
    private javax.swing.JLabel lblGenreToBeAddedSong;
    private javax.swing.JLabel lblGrammyToBeAdded;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblInstructionAdd;
    private javax.swing.JLabel lblInstructionsAddArtist;
    private javax.swing.JLabel lblInstructionsAddList;
    private javax.swing.JLabel lblInstructionsAddPlaylist;
    private javax.swing.JLabel lblInstructionsAddPlaylistBtn;
    private javax.swing.JLabel lblInstructionsAddSong;
    private javax.swing.JLabel lblInstructionsHeader;
    private javax.swing.JLabel lblInstructionsHeaderAddSong;
    private javax.swing.JLabel lblInstructionsHederAddList;
    private javax.swing.JLabel lblInstructionsSearchArtist;
    private javax.swing.JLabel lblInstructionsSearchListener;
    private javax.swing.JLabel lblInstructionsSearchPlaylist;
    private javax.swing.JLabel lblInstructionsSearchSong;
    private javax.swing.JLabel lblInstructionsToAddArtist;
    private javax.swing.JLabel lblLatestAlbumToBeAdded;
    private javax.swing.JLabel lblListenerIDToBeAdded;
    private javax.swing.JLabel lblListenerIDToBeAddedL;
    private javax.swing.JLabel lblListenerNotFound;
    private javax.swing.JLabel lblListenerToBeAdded;
    private javax.swing.JLabel lblLogoAddListener;
    private javax.swing.JLabel lblLogoAddPlaylist;
    private javax.swing.JLabel lblLogoAddSong;
    private javax.swing.JLabel lblLogoAddUser;
    private javax.swing.JLabel lblLogoListener;
    private javax.swing.JLabel lblLogoLogin;
    private javax.swing.JLabel lblLogoMenu;
    private javax.swing.JLabel lblLogoPlaylist;
    private javax.swing.JLabel lblLogoSong;
    private javax.swing.JLabel lblLogoSongTbl;
    private javax.swing.JLabel lblMenuInstructions;
    private javax.swing.JLabel lblMostStreamedToBeAdded;
    private javax.swing.JLabel lblNameToBeAdded;
    private javax.swing.JLabel lblNotFoundArtist;
    private javax.swing.JLabel lblNotFoundPlaylist;
    private javax.swing.JLabel lblNotFoundSong;
    private javax.swing.JLabel lblOtherOccupationToBeAdded;
    private javax.swing.JLabel lblPasswordLogin;
    private javax.swing.JLabel lblPhoneNumToBeAdded;
    private javax.swing.JLabel lblPlaylistPanelChange;
    private javax.swing.JLabel lblPrefferedGenreToBeAdded;
    private javax.swing.JLabel lblReleaseDateToBeAdded;
    private javax.swing.JLabel lblSearchArtist;
    private javax.swing.JLabel lblSearchArtistBtn;
    private javax.swing.JLabel lblSearchArtistIDBtn;
    private javax.swing.JLabel lblSearchArtistLogo;
    private javax.swing.JLabel lblSearchArtistPanelChange;
    private javax.swing.JLabel lblSearchListener;
    private javax.swing.JLabel lblSearchListenerBtn;
    private javax.swing.JLabel lblSearchListenerLogo;
    private javax.swing.JLabel lblSearchListenerPanelChange;
    private javax.swing.JLabel lblSearchPlaylist;
    private javax.swing.JLabel lblSearchPlaylistLogo;
    private javax.swing.JLabel lblSearchSong;
    private javax.swing.JLabel lblSearchSongLogo;
    private javax.swing.JLabel lblSongIDToBeAdded;
    private javax.swing.JLabel lblSongNameToBeAdded;
    private javax.swing.JLabel lblSongSearchPanelChange;
    private javax.swing.JLabel lblStreamingToBeAdded;
    private javax.swing.JLabel lblSucAddArtist;
    private javax.swing.JLabel lblSucAddListener;
    private javax.swing.JLabel lblSucAddPlaylist;
    private javax.swing.JLabel lblSucAddSong;
    private javax.swing.JLabel lblSurnameToBeAdded;
    private javax.swing.JLabel lblUpcomingToursToBeAdded;
    private javax.swing.JLabel lblUsernameLogin;
    private javax.swing.JLabel lblUsernameToBeAdded;
    private javax.swing.JLabel lblViewArtists;
    private javax.swing.JLabel lblViewListeners;
    private javax.swing.JLabel lblViewPlaylists;
    private javax.swing.JLabel lblViewSongs;
    private javax.swing.JLabel lblWebsiteToBeAdded;
    private javax.swing.JLabel lblWelcomeUser;
    private com.toedter.calendar.JDateChooser songReleaseDateToBeAdded;
    private javax.swing.JTable tblArtists;
    private javax.swing.JTable tblListeners;
    private javax.swing.JTable tblPlaylists;
    private javax.swing.JTable tblSearchArtist;
    private javax.swing.JTable tblSearchListener;
    private javax.swing.JTable tblSearchPlaylist;
    private javax.swing.JTable tblSearchSong;
    private javax.swing.JTable tblSongs;
    private javax.swing.JPasswordField txtFldAddPassword;
    private javax.swing.JTextField txtFldAddUser;
    private javax.swing.JTextField txtFldAlbumSaleToBeAdded;
    private javax.swing.JTextField txtFldAlbumToBeAdded;
    private javax.swing.JTextField txtFldArtistIDToBeAdded;
    private javax.swing.JTextField txtFldArtistIDToBeAddedA;
    private javax.swing.JTextField txtFldArtistToBeAdded;
    private javax.swing.JTextField txtFldArtistToSearchFor;
    private javax.swing.JTextField txtFldDurationToBeAdded;
    private javax.swing.JTextField txtFldGenreToBeAdded;
    private javax.swing.JTextField txtFldGenreToBeAddedSong;
    private javax.swing.JLabel txtFldInstructionsHeaderAddPlaylist;
    private javax.swing.JTextField txtFldLatestAlbumToBeAdded;
    private javax.swing.JTextField txtFldListenerIDToBeAdded;
    private javax.swing.JTextField txtFldListenerIDToBeAddedL;
    private javax.swing.JTextField txtFldListenerToBeAdded;
    private javax.swing.JTextField txtFldListenerToSearchFor;
    private javax.swing.JTextField txtFldMostStreamedToBeAdded;
    private javax.swing.JTextField txtFldNameToBeAdded;
    private javax.swing.JTextField txtFldOtherOccToBeAdded;
    private javax.swing.JPasswordField txtFldPassword;
    private javax.swing.JTextField txtFldPhoneNumToBeAdded;
    private javax.swing.JTextField txtFldPlaylistToSearchFor;
    private javax.swing.JTextField txtFldPrefferedGenreToBeAdded;
    private javax.swing.JTextField txtFldSongIDToBeAdded;
    private javax.swing.JTextField txtFldSongNameToBeAdded;
    private javax.swing.JTextField txtFldSongToSearchFor;
    private javax.swing.JTextField txtFldStreamingToBeAdded;
    private javax.swing.JTextField txtFldSurnameToBeAdded;
    private javax.swing.JTextField txtFldUpcomingTourToBeAdded;
    private javax.swing.JTextField txtFldUsername;
    private javax.swing.JTextField txtFldUsernameToBeAdded;
    private javax.swing.JTextField txtFldWebsiteToBeAdded;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent ke) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) 
    {
        //if statement used to check if the key pressed is deleted or not
        if(ke.getKeyCode() == KeyEvent.VK_DELETE)
        {
            //telling user that the key pressed has been deleted
            System.out.println("Key pressed has been deleted");
            
            //taking the amount of rows selected and storing them into the array
            int[] rows = tblArtists.getSelectedRows();
            
            //taking the amount of rows stored above and using them in the for loop
            for (int i = rows.length - 1; i >= 0; i--)
            {
                //using specific poiint in the row in table
                String code = (String) modelArtist.getValueAt(rows[i], 0);
                //using the deleteArtist created above to delete the selected artists
                System.out.println(artM.deleteArtist(code));
                //removing all data from tblArtists and reloading it to updaete the table
                clearArtistModel();
                loadArtists();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) 
    {
        
    }
}
