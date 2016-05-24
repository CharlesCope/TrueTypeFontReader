package Fonts;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import Fonts.table.CmapTable;
import Fonts.table.GlyfTable;
import Fonts.table.HeadTable;
import Fonts.table.HheaTable;
import Fonts.table.HmtxTable;
import Fonts.table.LocaTable;
import Fonts.table.MaxpTable;
import Fonts.table.NameTable;
import Fonts.table.Os2Table;
import Fonts.table.PostTable;
import Fonts.table.Table;
import Fonts.table.TableDirectory;
import Fonts.table.TableFactory;


/**
 * The TrueType font.
 */
public class ChcFont {

    private String path;
//    private Interpreter interp = null;
//    private Parser parser = null;
    private TableDirectory tableDirectory = null;
    private Table[] tables;
    private Os2Table os2;
    private CmapTable cmap;
    private GlyfTable glyf;
    private HeadTable head;
    private HheaTable hhea;
    private HmtxTable hmtx;
    private LocaTable loca;
    private MaxpTable maxp;
    private NameTable name;
    private PostTable post;

    /**
     * Constructor
     */
    public ChcFont() {
    }

    public Table getTable(int tableType) {
        for (int i = 0; i < tables.length; i++) {
            if ((tables[i] != null) && (tables[i].getType() == tableType)) {
                return tables[i];
            }
        }
        return null;
    }

    public Os2Table getOS2Table() {
        return os2;
    }
    
    public CmapTable getCmapTable() {
        return cmap;
    }
    
    public HeadTable getHeadTable() {
        return head;
    }
    
    public HheaTable getHheaTable() {
        return hhea;
    }
    
    public HmtxTable getHmtxTable() {
        return hmtx;
    }
    
    public LocaTable getLocaTable() {
        return loca;
    }
    
    public MaxpTable getMaxpTable() {
        return maxp;
    }

    public NameTable getNameTable() {
        return name;
    }

    public PostTable getPostTable() {
        return post;
    }

    public int getAscent() {
        return hhea.getAscender();
    }

    public int getDescent() {
        return hhea.getDescender();
    }

    public int getNumGlyphs() {
        return maxp.getNumGlyphs();
    }

    public Glyph getGlyph(int i) {
        return (glyf.getDescription(i) != null)
            ? new Glyph(
                glyf.getDescription(i),
                hmtx.getLeftSideBearing(i),
                hmtx.getAdvanceWidth(i))
            : null;
    }

    public String getPath() {
        return path;
    }

    public TableDirectory getTableDirectory() {
        return tableDirectory;
    }

    /**
     * @param pathName Path to the TTF font file
     */
    protected void read(String pathName) {
        path = pathName;
        File f = new File(pathName);

        if (!f.exists()) {
            // TODO: Throw TTException
            return;
        }

        try {
            RandomAccessFile raf = new RandomAccessFile(f, "r");
            tableDirectory = new TableDirectory(raf);
            tables = new Table[tableDirectory.getNumTables()];

            // Load each of the tables
            for (int i = 0; i < tableDirectory.getNumTables(); i++) {
                tables[i] = TableFactory.create
                    (tableDirectory.getEntry(i), raf);
            }
            raf.close();

            // Get references to commonly used tables
            os2  = (Os2Table) getTable(Table.OS_2);
            cmap = (CmapTable) getTable(Table.cmap);
            glyf = (GlyfTable) getTable(Table.glyf);
            head = (HeadTable) getTable(Table.head);
            hhea = (HheaTable) getTable(Table.hhea);
            hmtx = (HmtxTable) getTable(Table.hmtx);
            loca = (LocaTable) getTable(Table.loca);
            maxp = (MaxpTable) getTable(Table.maxp);
            name = (NameTable) getTable(Table.name);
            post = (PostTable) getTable(Table.post);

            // Initialize the tables that require it
            hmtx.init(hhea.getNumberOfHMetrics(), 
                      maxp.getNumGlyphs() - hhea.getNumberOfHMetrics());
            loca.init(maxp.getNumGlyphs(), head.getIndexToLocFormat() == 0);
            glyf.init(maxp.getNumGlyphs(), loca);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ChcFont create() {
        return new ChcFont();
    }
    
    /**
     * @param pathName Path to the TTF font file
     */
    public ChcFont create(String pathName) {
        ChcFont f = new ChcFont();
        f.read(pathName);
        return f;
    }
}