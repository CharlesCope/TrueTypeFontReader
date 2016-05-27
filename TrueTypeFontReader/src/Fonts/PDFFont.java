package Fonts;
/** Notes on Java Data Types.
 * byte: Byte data type is an 8-bit signed,Minimum value is -128, Maximum value is 127 (inclusive) 
 * short:Short data type is a 16-bit signed,Minimum value is -32,768, Maximum value is 32,767 (inclusive)
 * int:Int data type is a 32-bit signed,Minimum value is - 2,147,483,648, Maximum value is 2,147,483,647(inclusive) 
 * */
public class PDFFont {
	
	private String strBaseFontName = "";
	private String strFontBBox = "";
	private boolean blnFixedPitchFlag = false;
	private boolean blnSerifFlag = false;
	private boolean blnSymbolicFlag = false;
	private boolean blnScriptFlag = false;
	private boolean blnNonsymbolicFlag = false;
	private boolean blnItalicFlag = false;
	private boolean blnAllCapFlag = false;
	private boolean blnSmallCapFlag = false;
	private boolean blnForceBoldFlag = false;
	private int intUnitsPerEm = 0;
	private int BBoxLowerLeftx = 0;
	private int BBoxLowerLefty = 0;
	private int BBoxUpperRightx = 0;
	private int BBoxUpperRighty = 0;
	
	
	/**The Constructor*/
	public PDFFont(){}
	/** PDF Notes On Subject. 
	 * The PostScript name for the value of BaseFont is determined in one of two ways:
	 *  Use the PostScript name that is an optional entry in the “name” table of the TrueType font. 
	 *  
	 *  In the absence of such an entry in the “name” table, derive a PostScript name
	 *  from the name by which the font is known in the host operating system. On a
	 *  Windows system, the name is based on the lfFaceName field in a LOGFONT
	 *  structure; in the Mac OS, it is based on the name of the FOND resource. If the
	 *  name contains any spaces, the spaces are removed.        
	 */
	public String getFontBaseName(){return strBaseFontName;}
	/** The value of the Flags entry in a font descriptor is an unsigned 32-bit(big-endian integer containing
	 * flags specifying various characteristics of the font.
	 * Example "0000 0000 0000 0100 0000 0000 0010 0010"
	 * Decimal Value = 262178
	 * Flags Set 2,6,19
	*/ 
	public String getFontDescriptorFlags(){
		
		// big-endian string
		StringBuilder str32Flag = new StringBuilder("00000000000000000000000000000000");
		final int RADIX = 10;
	
		// set our flag bits
		str32Flag.setCharAt(31, Character.forDigit(Boolean.compare(blnFixedPitchFlag,false), RADIX));
		str32Flag.setCharAt(30, Character.forDigit(Boolean.compare(blnSerifFlag,false), RADIX));
		str32Flag.setCharAt(29, Character.forDigit(Boolean.compare(blnSymbolicFlag,false), RADIX));
		str32Flag.setCharAt(28, Character.forDigit(Boolean.compare(blnScriptFlag,false), RADIX));
		// 5 Flag not Used
		str32Flag.setCharAt(26, Character.forDigit(Boolean.compare(blnNonsymbolicFlag,false), RADIX));
		str32Flag.setCharAt(25, Character.forDigit(Boolean.compare(blnItalicFlag,false), RADIX));
		// 8 to 16 Flags not Used
		str32Flag.setCharAt(15, Character.forDigit(Boolean.compare(blnAllCapFlag,false), RADIX));
		str32Flag.setCharAt(14, Character.forDigit(Boolean.compare(blnSmallCapFlag,false), RADIX));
		str32Flag.setCharAt(13, Character.forDigit(Boolean.compare(blnForceBoldFlag,false), RADIX));
		// 12 to 0 Flags not Used
		int intFlagsVaule = Integer.parseUnsignedInt(str32Flag.toString(), 2);
		return "/Flags " + intFlagsVaule;
		
	}
	/** Sources for the code I used...
	 *https://github.com/zendframework/zf1/blob/master/library/Zend/Pdf/Resource/Font/FontDescriptor.php Line:116
	 * http://www.websupergoo.com/helppdfnet/default.htm?page=source%2Fdefault.htm
	 * This XRect corresponds to the xMin, xMax, yMin and yMax entries in the 'head' TrueType table.
	 * Distances are measured up and right in points. Points are a traditional measure for print work and there are 72 points in an inch 
	 * Notes on Font Bounding Box
	 * PDF Rectangles:
	 * rectangle is written as an array of four numbers giving the coordinates of a pair of 
	 * diagonally opposite corners. Typically, the array takes the form [ lowerLeftx lowerLefty upperRightx upperRighty ]
	 * */
	public String getFontBBox(){
		// Results not matching data in file
		int lowerLeftx = 0;
		int lowerLefty = 0;
		int upperRightx = 0;
		int upperRighty =0;
		
		lowerLeftx = (int) toEmSpace(BBoxLowerLeftx);
		lowerLefty = (int) toEmSpace(BBoxLowerLefty);
		upperRightx = (int) toEmSpace(BBoxUpperRightx);
		upperRighty= (int) toEmSpace(BBoxUpperRighty);
		
		strFontBBox = "/FontBBox [";
		strFontBBox += String.valueOf(lowerLeftx) + " ";
		strFontBBox += String.valueOf(lowerLefty) + " ";
		strFontBBox += String.valueOf(upperRightx) + " ";
		strFontBBox += String.valueOf(upperRighty) + "] ";
		
		return strFontBBox;
		
	}
	public int[] getGlyphWidthsToPDFWidths(){
		return null;
		
	}
	public void setFontBaseName(String strName){strBaseFontName = strName;}
	
	/** Our Flags for the font Descriptor */
	public void setFixedPitchFlag(boolean setFlag){blnFixedPitchFlag = setFlag;}
	
	public void setSerifFlag(boolean setFlag){blnSerifFlag = setFlag;}	
	
	public void setSymbolicFlag(boolean setFlag){blnSymbolicFlag = setFlag;}
	
	public void setScriptFlag(boolean setFlag){blnScriptFlag = setFlag;}
	
	public void setNonsymbolicFlag(boolean setFlag){blnNonsymbolicFlag = setFlag;}
	
	public void setItalicFlag(boolean setFlag){blnItalicFlag = setFlag;}
	
	public void setAllCapFlag(boolean setFlag){blnAllCapFlag = setFlag;}
	
	public void setSmallCapFlag(boolean setFlag){blnSmallCapFlag = setFlag;}
	
	public void setForceBoldFlag(boolean setFlag){blnForceBoldFlag = setFlag;}
	
	public int getUnitsPerEm() {return intUnitsPerEm;}
	
	public void setUnitsPerEm(int UnitsPerEm) {intUnitsPerEm = UnitsPerEm;}
	
	public int getBoundingBoxLowerLeftx() {return BBoxLowerLeftx;}
	public int getBoundingBoxLowerLefty() {return BBoxLowerLefty;}
	public int getBoundingBoxUpperRightx() {return BBoxUpperRightx;}
	public int getBoundingBoxUpperRighty() {return BBoxUpperRighty;}
	
	public void setBoundingBoxLowerLeftx(int bBoxLowerLeftx) {BBoxLowerLeftx = bBoxLowerLeftx;}
	public void setBoundingBoxLowerLefty(int bBoxLowerLefty) {BBoxLowerLefty = bBoxLowerLefty;}
	public void setBoundingBoxUpperRightx(int bBoxUpperRightx) {BBoxUpperRightx = bBoxUpperRightx;}
	public void setBoundingBoxUpperRighty(int bBoxUpperRighty) {BBoxUpperRighty = bBoxUpperRighty;}
	
	/** https://github.com/zendframework/zf1/blob/master/library/Zend/Pdf/Resource/Font.php Line:522
	 *  If the font's glyph space is not 1000 units per em, converts the value. */
    public double toEmSpace(double dblValue){
	        if (intUnitsPerEm == 1000) {return dblValue;}
	        return Math.ceil((dblValue / intUnitsPerEm) * 1000);    // always round up
	    }
	
	/** Need a toString Method for debugging and development */
	
	public String toString(){
		String JavaNewLine = System.getProperty("line.separator");
		String strToString = "";
		
		strToString = "BaseFont Name >> " + strBaseFontName + JavaNewLine;
		strToString += "Flags Values >> " + getFontDescriptorFlags() + JavaNewLine;
		strToString += "Font Bounding Box >> " + getFontBBox() + JavaNewLine;
		return strToString;
		
	}
	
	
	
	
	
}
