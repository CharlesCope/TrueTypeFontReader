package Fonts;

public class PDFFont {
	private boolean blnFixedPitchFlag = false;
	private boolean blnSerifFlag = false;
	private boolean blnSymbolicFlag = false;
	private boolean blnScriptFlag = false;
	private boolean blnNonsymbolicFlag = false;
	private boolean blnItalicFlag = false;
	private boolean blnAllCapFlag = false;
	private boolean blnSmallCapFlag = false;
	private boolean blnForceBoldFlag = false;
	
	/**The Constructor*/
	public PDFFont(){}
	
	public String getFontDescriptorFlags(){
		/** The value of the Flags entry in a font descriptor is an unsigned 32-bit(big-endian integer containing
		 * flags specifying various characteristics of the font.
		 * Example "0000 0000 0000 0100 0000 0000 0010 0010"
		 * Decimal Value = 262178
		 * Flags Set 2,6,19
		*/ 
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
	
	
	
	
	
	
	
	
	
}
