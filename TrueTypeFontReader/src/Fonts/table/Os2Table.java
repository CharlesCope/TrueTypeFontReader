package Fonts.table;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Os2Table implements Table {

    private int version;
    private short xAvgCharWidth;
    private int usWeightClass;
    private int usWidthClass;
    private short fsType;
    private short ySubscriptXSize;
    private short ySubscriptYSize;
    private short ySubscriptXOffset;
    private short ySubscriptYOffset;
    private short ySuperscriptXSize;
    private short ySuperscriptYSize;
    private short ySuperscriptXOffset;
    private short ySuperscriptYOffset;
    private short yStrikeoutSize;
    private short yStrikeoutPosition;
    private short sFamilyClass;
    private Panose panose;
    private int ulUnicodeRange1;
    private int ulUnicodeRange2;
    private int ulUnicodeRange3;
    private int ulUnicodeRange4;
    private int achVendorID;
    private short fsSelection;
    private int usFirstCharIndex;
    private int usLastCharIndex;
    private short sTypoAscender;
    private short sTypoDescender;
    private short sTypoLineGap;
    private int usWinAscent;
    private int usWinDescent;
    private int ulCodePageRange1;
    private int ulCodePageRange2;
    private short sxHeight;
    private short sCapHeight;
    private int usDefaultChar;
    private int usBreakChar;

    protected Os2Table(DirectoryEntry de,RandomAccessFile raf) throws IOException {
        raf.seek(de.getOffset());
        version = raf.readUnsignedShort();
        xAvgCharWidth = raf.readShort();
        usWeightClass = raf.readUnsignedShort();
        usWidthClass = raf.readUnsignedShort();
        fsType = raf.readShort();
        ySubscriptXSize = raf.readShort();
        ySubscriptYSize = raf.readShort();
        ySubscriptXOffset = raf.readShort();
        ySubscriptYOffset = raf.readShort();
        ySuperscriptXSize = raf.readShort();
        ySuperscriptYSize = raf.readShort();
        ySuperscriptXOffset = raf.readShort();
        ySuperscriptYOffset = raf.readShort();
        yStrikeoutSize = raf.readShort();
        yStrikeoutPosition = raf.readShort();
        sFamilyClass = raf.readShort();
        byte[] buf = new byte[10];
        raf.read(buf);
        panose = new Panose(buf);
        ulUnicodeRange1 = raf.readInt();
        ulUnicodeRange2 = raf.readInt();
        ulUnicodeRange3 = raf.readInt();
        ulUnicodeRange4 = raf.readInt();
        achVendorID = raf.readInt();
        fsSelection = raf.readShort();
        usFirstCharIndex = raf.readUnsignedShort();
        usLastCharIndex = raf.readUnsignedShort();
        sTypoAscender = raf.readShort();
        sTypoDescender = raf.readShort();
        sTypoLineGap = raf.readShort();
        usWinAscent = raf.readUnsignedShort();
        usWinDescent = raf.readUnsignedShort();
        ulCodePageRange1 = raf.readInt();
        ulCodePageRange2 = raf.readInt();
        sxHeight = raf.readShort();
        sCapHeight = raf.readShort();
        usDefaultChar = raf.readUnsignedShort();
        usBreakChar = raf.readUnsignedShort();
    }

    public int getVersion() {return version;}

    public short getAvgCharWidth() {return xAvgCharWidth;}

    public int getWeightClass() {
    	// Only possible good values are 1-9
    	int firstDigit = Integer.parseInt(Integer.toString(usWeightClass).substring(0, 1));
    	return firstDigit;}

    public int getWidthClass() {return usWidthClass;}

    public short getLicenseType() {return fsType;}

    public short getSubscriptXSize() {return ySubscriptXSize;}

    public short getSubscriptYSize() {return ySubscriptYSize;}

    public short getSubscriptXOffset() {return ySubscriptXOffset;}

    public short getSubscriptYOffset() {return ySubscriptYOffset;}

    public short getSuperscriptXSize() {return ySuperscriptXSize;}

    public short getSuperscriptYSize() {return ySuperscriptYSize;}

    public short getSuperscriptXOffset() {return ySuperscriptXOffset;}

    public short getSuperscriptYOffset() {return ySuperscriptYOffset;}

    public short getStrikeoutSize() {return yStrikeoutSize;}

    public short getStrikeoutPosition() {return yStrikeoutPosition;}

    public short getFamilyClass() {return sFamilyClass;}

    public Panose getPanose() {return panose;}

    public int getUnicodeRange1() {return ulUnicodeRange1;}

    public int getUnicodeRange2() {return ulUnicodeRange2;}

    public int getUnicodeRange3() {return ulUnicodeRange3;}

    public int getUnicodeRange4() {return ulUnicodeRange4;}

    public int getVendorID() {return achVendorID;}

    public short getSelection() {return fsSelection;}

    public int getFirstCharIndex() {return usFirstCharIndex;}

    public int getLastCharIndex() {return usLastCharIndex;}

    public short getTypoAscender() {return sTypoAscender;}

    public short getTypoDescender() {return sTypoDescender;}

    public short getTypoLineGap() {return sTypoLineGap;}

    public int getWinAscent() {return usWinAscent;}

    public int getWinDescent() {return usWinDescent;}

    public int getCodePageRange1() {return ulCodePageRange1;}

    public int getCodePageRange2() {return ulCodePageRange2;}
    
    public short getXHeight(){return sxHeight;}
    
    public short getCapHeight(){return sCapHeight;}
    
    public int getDefaultChar(){return usDefaultChar;}
    
    public int getWinBreakChar(){return usBreakChar;}
    
    
    
    
    public boolean getIsSerif(){
    	/** The high byte of this field contains the family class, while the low byte contains the family subclass.*/
    	byte data = (byte) ((getFamilyClass() & 0xFF00) >> 8);
		int intHighBit = Byte.toUnsignedInt(data);
		if (intHighBit == 8){ return false;} 
    	return true;
    	
    }
    public boolean getIsScript(){
    	byte data = (byte) ((getFamilyClass() & 0xFF00) >> 8);
    	int intHighBit = Byte.toUnsignedInt(data);
    	if (intHighBit == 10){ return true;}
		return false; 
    }
    
    public int getType() {return OS_2;}
}