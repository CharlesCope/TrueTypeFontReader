package Fonts.table;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;


public class HmtxTable implements Table {

    private byte[] buf = null;
    private int[] hMetrics = null;
    private short[] leftSideBearing = null;

    protected HmtxTable(DirectoryEntry de,RandomAccessFile raf) throws IOException {
        raf.seek(de.getOffset());
        buf = new byte[de.getLength()];
        raf.read(buf);
/*
        TableMaxp t_maxp = (TableMaxp) td.getEntryByTag(maxp).getTable();
        TableHhea t_hhea = (TableHhea) td.getEntryByTag(hhea).getTable();
        int lsbCount = t_maxp.getNumGlyphs() - t_hhea.getNumberOfHMetrics();
        hMetrics = new int[t_hhea.getNumberOfHMetrics()];
        for (int i = 0; i < t_hhea.getNumberOfHMetrics(); i++) {
            hMetrics[i] = raf.readInt();
        }
        if (lsbCount > 0) {
            leftSideBearing = new short[lsbCount];
            for (int i = 0; i < lsbCount; i++) {
                leftSideBearing[i] = raf.readShort();
            }
        }
*/
    }

    public void init(int numberOfHMetrics, int lsbCount) {
        if (buf == null) {return;}
        
        hMetrics = new int[numberOfHMetrics];
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
        for (int i = 0; i < numberOfHMetrics; i++) {
            hMetrics[i] = (bais.read()<<24 | bais.read()<<16 | 
                           bais.read()<< 8 | bais.read());
        }
        if (lsbCount > 0) {
            leftSideBearing = new short[lsbCount];
            for (int i = 0; i < lsbCount; i++) {
                leftSideBearing[i] = (short)(bais.read()<<8 | bais.read());
            }
        }
        buf = null;
    }

    public int getAdvanceWidth(int i) {
        if (hMetrics == null) {return 0;}
        if (i < hMetrics.length) {return hMetrics[i] >> 16;}
        else {return hMetrics[hMetrics.length - 1] >> 16;}
    }

    public short getLeftSideBearing(int i) {
        if (hMetrics == null) {return 0;}
        if (i < hMetrics.length) {return (short)(hMetrics[i] & 0xffff);}
        else {return leftSideBearing[i - hMetrics.length];}
    }

    public int getType() {return hmtx;}
}