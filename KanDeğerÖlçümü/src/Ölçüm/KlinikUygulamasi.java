package Ölçüm;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KlinikUygulamasi{

  
    static final String DB_URL = getDbUrl();
    
    static DefaultTableModel model = new DefaultTableModel();
    static JLabel lblToplamHasta, lblBugunkuKayit;
    
 
    static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    static final Color SUCCESS_COLOR = new Color(39, 174, 96);
    static final Color DANGER_COLOR = new Color(231, 76, 60);
    static final Color WARNING_COLOR = new Color(243, 156, 18);
    static final Color DARK_BG = new Color(44, 62, 80);
    static final Color LIGHT_BG = new Color(236, 240, 241);
    static final Color WHITE = Color.WHITE;

    private static String getDbUrl() {
        String dbServer = System.getenv("DB_SERVER");
        String dbPort = System.getenv("DB_PORT");
        String dbName = System.getenv("DB_NAME");
        
        if (dbServer == null) dbServer = "localhost";
        if (dbPort == null) dbPort = "1433";
        if (dbName == null) dbName = "KlinikDB";
        
        return String.format(
            "jdbc:sqlserver://%s:%s;databaseName=%s;integratedSecurity=true;encrypt=true;trustServerCertificate=true;",
            dbServer, dbPort, dbName
        );
    }

    public static void main(String[] args) {
     
        if (!testDatabaseConnection()) {
            JOptionPane.showMessageDialog(null, 
                "⚠️ Veritabanı bağlantısı kurulamadı!\n\n" +
                "Lütfen şunları kontrol edin:\n" +
                "1. SQL Server çalışıyor mu?\n" +
                "2. Ortam değişkenleri ayarlandı mı?\n" +
                "3. Veritabanı mevcut mu?\n\n" +
                "Detaylar için README.md dosyasına bakın.",
                "Bağlantı Hatası", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("🏥 Hastane Otomasyon Sistemi - Profesyonel Panel");
        frame.setSize(1400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(0, 0));
        frame.setLocationRelativeTo(null);

    
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(DARK_BG);
        headerPanel.setPreferredSize(new Dimension(1400, 80));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel lblTitle = new JLabel("🏥 HASTANE YÖNETİM SİSTEMİ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(WHITE);

        JLabel lblSubtitle = new JLabel("Hasta Kayıt ve Takip Modülü");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(189, 195, 199));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setOpaque(false);
        titlePanel.add(lblTitle);
        titlePanel.add(lblSubtitle);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
        JLabel lblTarih = new JLabel(sdf.format(new Date()));
        lblTarih.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTarih.setForeground(new Color(189, 195, 199));

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(lblTarih, BorderLayout.EAST);

        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(LIGHT_BG);
        leftPanel.setPreferredSize(new Dimension(320, 720));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel statsPanel = new JPanel(new GridLayout(3, 1, 0, 15));
        statsPanel.setOpaque(false);

        JPanel cardToplamHasta = createStatCard("👥 Toplam Hasta", "0", PRIMARY_COLOR);
        JPanel cardBugunkuKayit = createStatCard("📅 Bugünkü Kayıt", "0", SUCCESS_COLOR);
        JPanel cardSistemDurumu = createStatCard("⚡ Sistem", "AKTİF", WARNING_COLOR);

        lblToplamHasta = (JLabel) cardToplamHasta.getComponent(1);
        lblBugunkuKayit = (JLabel) cardBugunkuKayit.getComponent(1);

        statsPanel.add(cardToplamHasta);
        statsPanel.add(cardBugunkuKayit);
        statsPanel.add(cardSistemDurumu);

        leftPanel.add(statsPanel);
        leftPanel.add(Box.createVerticalStrut(30));

        JButton btnYeniHasta = createModernButton("➕ YENİ HASTA EKLE", SUCCESS_COLOR);
        JButton btnHastaSil = createModernButton("🗑️ HASTA SİL", DANGER_COLOR);
        JButton btnYenile = createModernButton("🔄 LİSTEYİ YENİLE", new Color(52, 152, 219));
        JButton btnVerileriTemizle = createModernButton("⚠️ TÜM VERİLERİ TEMİZLE", DANGER_COLOR);

        leftPanel.add(btnYeniHasta);
        leftPanel.add(Box.createVerticalStrut(12));
        leftPanel.add(btnHastaSil);
        leftPanel.add(Box.createVerticalStrut(12));
        leftPanel.add(btnYenile);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(btnVerileriTemizle);

       
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        searchPanel.setOpaque(false);

        JLabel lblArama = new JLabel("🔍 Hasta Ara:");
        lblArama.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JTextField txtArama = new JTextField(30);
        txtArama.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtArama.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(189, 195, 199), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        JButton btnAra = createSmallButton("ARA", PRIMARY_COLOR);

        searchPanel.add(lblArama);
        searchPanel.add(txtArama);
        searchPanel.add(btnAra);

        model.setColumnIdentifiers(new String[]{"ID", "TC KİMLİK", "AD", "SOYAD", "ŞİKAYET/TANI", "KAYIT TARİHİ"});
        
        JTable tablo = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablo.setRowHeight(35);
        tablo.setSelectionBackground(new Color(52, 152, 219));
        tablo.setSelectionForeground(WHITE);
        tablo.setShowGrid(true);
        tablo.setGridColor(new Color(189, 195, 199));
        
        JTableHeader header = tablo.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(DARK_BG);
        header.setForeground(WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablo.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablo.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        tablo.getColumnModel().getColumn(0).setPreferredWidth(50);
        tablo.getColumnModel().getColumn(1).setPreferredWidth(120);
        tablo.getColumnModel().getColumn(2).setPreferredWidth(120);
        tablo.getColumnModel().getColumn(3).setPreferredWidth(120);
        tablo.getColumnModel().getColumn(4).setPreferredWidth(250);
        tablo.getColumnModel().getColumn(5).setPreferredWidth(160);

        JScrollPane scrollPane = new JScrollPane(tablo);
        scrollPane.setBorder(new LineBorder(new Color(189, 195, 199), 1));

        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        verileriYenile();
        istatistikleriGuncelle();

    
        
        btnYeniHasta.addActionListener(e -> {
            JTextField txtTc = new JTextField(11);
            JTextField txtAd = new JTextField(20);
            JTextField txtSoyad = new JTextField(20);
            JTextField txtSikayet = new JTextField(30);

            JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            panel.add(new JLabel("TC Kimlik No:"));
            panel.add(txtTc);
            panel.add(new JLabel("Ad:"));
            panel.add(txtAd);
            panel.add(new JLabel("Soyad:"));
            panel.add(txtSoyad);
            panel.add(new JLabel("Şikayet/Tanı:"));
            panel.add(txtSikayet);

            int result = JOptionPane.showConfirmDialog(frame, panel, 
                "Yeni Hasta Kaydı", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String tc = txtTc.getText().trim();
                String ad = txtAd.getText().trim();
                String soyad = txtSoyad.getText().trim();
                String sikayet = txtSikayet.getText().trim();

                if (tc.isEmpty() || ad.isEmpty() || soyad.isEmpty() || sikayet.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Tüm alanları doldurunuz!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (tc.length() != 11 || !tc.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "TC Kimlik No 11 haneli sayı olmalıdır!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                kaydet(tc, ad, soyad, sikayet);
                verileriYenile();
                istatistikleriGuncelle();
                JOptionPane.showMessageDialog(frame, "Hasta başarıyla kaydedildi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnHastaSil.addActionListener(e -> {
            int selectedRow = tablo.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Lütfen silmek istediğiniz hastayı seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String hastaId = model.getValueAt(selectedRow, 0).toString();
            String ad = model.getValueAt(selectedRow, 2).toString();
            String soyad = model.getValueAt(selectedRow, 3).toString();

            int confirm = JOptionPane.showConfirmDialog(frame, 
                ad + " " + soyad + " isimli hasta silinecek. Onaylıyor musunuz?", 
                "Hasta Silme Onayı", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                hastaSil(hastaId);
                verileriYenile();
                istatistikleriGuncelle();
                JOptionPane.showMessageDialog(frame, "Hasta başarıyla silindi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnYenile.addActionListener(e -> {
            verileriYenile();
            istatistikleriGuncelle();
        });

        btnVerileriTemizle.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, 
                "⚠️ DİKKAT! Tüm hasta kayıtları silinecek!\nBu işlem geri alınamaz. Devam etmek istiyor musunuz?", 
                "Kritik Uyarı", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                int secondConfirm = JOptionPane.showConfirmDialog(frame, 
                    "Son kez soruyorum: TÜM VERİLER SİLİNECEK. Emin misiniz?", 
                    "Kritik Uyarı", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                
                if (secondConfirm == JOptionPane.YES_OPTION) {
                    tumVerileriTemizle();
                    verileriYenile();
                    istatistikleriGuncelle();
                    JOptionPane.showMessageDialog(frame, "Tüm veriler temizlendi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnAra.addActionListener(e -> {
            String arananKelime = txtArama.getText().trim().toLowerCase();
            if (arananKelime.isEmpty()) {
                verileriYenile();
                return;
            }

            model.setRowCount(0);
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                String sql = "SELECT * FROM Hastalar WHERE " +
                           "LOWER(TC_Kimlik) LIKE ? OR " +
                           "LOWER(Ad) LIKE ? OR " +
                           "LOWER(Soyad) LIKE ? OR " +
                           "LOWER(Sikayet) LIKE ?";
                
                PreparedStatement pstmt = conn.prepareStatement(sql);
                String searchPattern = "%" + arananKelime + "%";
                pstmt.setString(1, searchPattern);
                pstmt.setString(2, searchPattern);
                pstmt.setString(3, searchPattern);
                pstmt.setString(4, searchPattern);
                
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("HastaID"),
                        rs.getString("TC_Kimlik"),
                        rs.getString("Ad"),
                        rs.getString("Soyad"),
                        rs.getString("Sikayet"),
                        rs.getString("KayitTarihi")
                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        frame.setVisible(true);
    }

    
    private static boolean testDatabaseConnection() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            return conn != null;
        } catch (SQLException e) {
            System.err.println("Veritabanı bağlantı hatası: " + e.getMessage());
            return false;
        }
    }

   
    private static JPanel createStatCard(String baslik, String deger, Color renk) {
        JPanel card = new JPanel(new BorderLayout(10, 5));
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(renk, 2, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblBaslik = new JLabel(baslik);
        lblBaslik.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblBaslik.setForeground(new Color(127, 140, 141));

        JLabel lblDeger = new JLabel(deger);
        lblDeger.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblDeger.setForeground(renk);

        card.add(lblBaslik, BorderLayout.NORTH);
        card.add(lblDeger, BorderLayout.CENTER);

        return card;
    }

    private static JButton createModernButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(color);
        btn.setForeground(WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(280, 45));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(color);
            }
        });
        
        return btn;
    }

    private static JButton createSmallButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(color);
        btn.setForeground(WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(80, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return btn;
    }



    public static void kaydet(String tc, String ad, String soyad, String sikayet) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO Hastalar (TC_Kimlik, Ad, Soyad, Sikayet, KayitTarihi) VALUES (?, ?, ?, ?, GETDATE())";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tc);
            pstmt.setString(2, ad);
            pstmt.setString(3, soyad);
            pstmt.setString(4, sikayet);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void hastaSil(String hastaId) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "DELETE FROM Hastalar WHERE HastaID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hastaId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void tumVerileriTemizle() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.createStatement().executeUpdate("DELETE FROM Hastalar");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void verileriYenile() {
        model.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Hastalar ORDER BY HastaID DESC");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("HastaID"),
                    rs.getString("TC_Kimlik"), 
                    rs.getString("Ad"), 
                    rs.getString("Soyad"), 
                    rs.getString("Sikayet"),
                    rs.getString("KayitTarihi")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void istatistikleriGuncelle() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT COUNT(*) FROM Hastalar");
            if (rs1.next()) {
                lblToplamHasta.setText(String.valueOf(rs1.getInt(1)));
            }

            ResultSet rs2 = conn.createStatement().executeQuery(
                "SELECT COUNT(*) FROM Hastalar WHERE CONVERT(date, KayitTarihi) = CONVERT(date, GETDATE())"
            );
            if (rs2.next()) {
                lblBugunkuKayit.setText(String.valueOf(rs2.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}