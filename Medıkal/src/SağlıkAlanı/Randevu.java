package SağlıkAlanı;

public class Randevu {
	private String RandevuNo;
	private Hasta hasta;
	private String doktorAdi;
	private String tarih;
	private String bolum;
	
	public Randevu(String RandevuNo,Hasta hasta,String doktorAdi,String tarih,String bolum) {
		this.RandevuNo = RandevuNo;
		this.hasta=hasta;
		this.doktorAdi=doktorAdi;
		this.tarih=tarih;
		this.bolum=bolum;
	}
	
	public String getRandevuNo() {return RandevuNo;}
	public void setRandevuNo(String RandevuNo) {this.RandevuNo = RandevuNo;}
	
	public Hasta getHasta() {return hasta;}
	public void setHasta(Hasta hasta) {this.hasta = hasta;}
	
	public String getDoktorAdi() {return doktorAdi;}
	public void setDoktorAdi(String doktorAdi) {this.doktorAdi=doktorAdi;}
	
	public String gettarih() {return tarih;}
	public void settarih(String tarih) {this.tarih = tarih;}
	
	public String getBolum() {return bolum;}
	public void setBolum(String bolum) {this.bolum = bolum;}
	
	@Override
    public String toString() {
        return "Randevu No: " + RandevuNo + " Tarih: " + tarih +   "  Bölüm: " + bolum + 
               " Doktor: Dr. " + doktorAdi +
               "\n" + hasta.toString(); 
             
               
}
}
	
	
	
	


