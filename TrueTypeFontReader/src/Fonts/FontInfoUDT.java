package Fonts;

public class FontInfoUDT {
	private String indicator = "";
	private String Unicode ="";
	private String ASCII ="";
	private String Symbol ="";
	private String GlyphID ="";
	private String PDFWidth = "";

	public String getIndicator() {return indicator;}
	public String getUnicode() {return Unicode;}
	public String getASCII() {return ASCII;}
	public String getSymbol() {return Symbol;}
	public String getGlyphID() {return GlyphID;}
	public String getPDFWidth() {return PDFWidth;}
	
	public void setIndicator(String indicator) {this.indicator = indicator;}
	public void setUnicode(String unicode) {Unicode = unicode;}
	public void setASCII(String aSCII) {ASCII = aSCII;}
	public void setSymbol(String symbol) {Symbol = symbol;}
	public void setGlyphID(String glyphID) {GlyphID = glyphID;}
	public void setPDFWidth(String pDFWidth) {PDFWidth = pDFWidth;}
	
}
