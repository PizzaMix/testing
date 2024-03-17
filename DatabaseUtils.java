public class Database extends Object 
{
    protected Connection m_Conn = null;

  
  
    public static String getTypeString(int type)
    {
        String ret = null;

        switch(type)
        {
            case Types.ARRAY :
                ret = "ARRAY";
                break;
            case Types.BIGINT :
                ret = "BIGINT";
                break;
            case Types.BINARY :
                ret = "BINARY";
                break;
            case Types.BIT :
                ret = "BIT";
                break;
            case Types.BLOB :
                ret = "BLOB";
                break;
            case Types.BOOLEAN :
                ret = "BOOLEAN";
                break;
            case Types.CHAR :
                ret = "CHAR";
                break;
            case Types.CLOB :
                ret = "CLOB";
                break;
            case Types.DATALINK :
                ret = "DATALINK";
                break;
            case Types.DATE :
                ret = "DATE";
                break;
            case Types.DECIMAL :
                ret = "DECIMAL";
                break;
            case Types.DISTINCT :
                ret = "DISTINCT";
                break;
            case Types.DOUBLE :
                ret = "DOUBLE";
                break;
            case Types.FLOAT :
                ret = "FLOAT";
                break;
            case Types.INTEGER :
                ret = "INTEGER";
                break;
            case Types.JAVA_OBJECT :
                ret = "JAVA_OBJECT";
                break;
            case Types.LONGVARBINARY :
                ret = "LONGVARBINARY";
                break;
            case Types.LONGVARCHAR :
                ret = "LONGVARCHAR";
                break;
            case Types.NULL :
                ret = "NULL";
                break;
            case Types.NUMERIC :
                ret = "NUMERIC";
                break;
            case Types.OTHER :
                ret = "OTHER";
                break;
            case Types.REAL :
                ret = "REAL";
                break;
            case Types.REF :
                ret = "REF";
                break;
            case Types.SMALLINT :
                ret = "SMALLINT";
                break;
            case Types.STRUCT :
                ret = "STRUCT";
                break;
            case Types.TIME :
                ret = "TIME";
                break;
            case Types.TIMESTAMP :
                ret = "TIMESTAMP";
                break;
            case Types.TINYINT :
                ret = "TINYINT";
                break;
            case Types.VARBINARY :
                ret = "VARBINARY";
                break;
            case Types.VARCHAR :
                ret = "VARCHAR";
                break;
            default : 
                ret = "UNKNOWN TYPE";
                break;
        }
        
        return ret;
    }

 public void describe(String sel, PrintWriter w, boolean flush) throws SQLException
    {
        Statement s = m_Conn.createStatement();
        ResultSet rs = null;
        try
        {
            rs = s.executeQuery(sel);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            int colcount = rsmd.getColumnCount();

            for (int i = 1; i <= colcount; i++)
            {
                String lbl = rsmd.getColumnLabel(i);
                w.write(lbl);
                w.write(",type=");
                String tn = rsmd.getColumnTypeName(i);
                w.write(tn);
                w.write(",dispsize=");
                w.write(String.valueOf(rsmd.getColumnDisplaySize(i)));
                w.write(",precision=");
                w.write(String.valueOf(rsmd.getPrecision(i)));
                w.write(",scale=");
                w.write(String.valueOf(rsmd.getScale(i)));
                w.write(",jclass=");
                w.write(rsmd.getColumnClassName(i));
                w.write(",jdbc type=");
                w.write(Database.getTypeString(rsmd.getColumnType(i)));
                w.println();               
            }
            if (flush) w.flush();
        }
        finally
        {
            if (rs != null) rs.close();
            if (s != null) s.close();
        }
    }
}
