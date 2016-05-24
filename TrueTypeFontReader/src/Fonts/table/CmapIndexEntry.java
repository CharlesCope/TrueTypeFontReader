package Fonts.table;

import java.io.IOException;
import java.io.RandomAccessFile;


public class CmapIndexEntry {

    private int platformId;
    private int encodingId;
    private int offset;

    protected CmapIndexEntry(RandomAccessFile raf) throws IOException {
        platformId = raf.readUnsignedShort();
        encodingId = raf.readUnsignedShort();
        offset = raf.readInt();
    }

    public int getEncodingId() {return encodingId;}

    public int getOffset() {return offset;}

    public int getPlatformId() {return platformId;}

    public String toString() {
        String platform;
        String encoding = "";

        switch (platformId) {
            case 1: platform = " (Macintosh)"; break;
            case 3: platform = " (Windows)"; break;
            default: platform = "";
        }
        if (platformId == 3) {
            // Windows specific encodings
            switch (encodingId) {
                case 0: encoding = " (Symbol)"; break;
                case 1: encoding = " (Unicode)"; break;
                case 2: encoding = " (ShiftJIS)"; break;
                case 3: encoding = " (Big5)"; break;
                case 4: encoding = " (PRC)"; break;
                case 5: encoding = " (Wansung)"; break;
                case 6: encoding = " (Johab)"; break;
                default: encoding = "";
            }
        }
        return new StringBuffer()
        .append( "platform id: " )
        .append( platformId )
        .append( platform )
        .append( ", encoding id: " )
        .append( encodingId )
        .append( encoding )
        .append( ", offset: " )
        .append( offset ).toString();
    }
}
