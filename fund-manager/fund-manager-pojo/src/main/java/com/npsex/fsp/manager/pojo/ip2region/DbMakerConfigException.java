package com.npsex.fsp.manager.pojo.ip2region;

/**
 * configuration exception
 * 
 * @author DongWen
*/
public class DbMakerConfigException extends Exception
{
    private static final long serialVersionUID = 4495714680349884838L;
    
    public DbMakerConfigException( String info )
    {
        super(info);
    }
    
    public DbMakerConfigException( Throwable res )
    {
        super(res);
    }
    
    public DbMakerConfigException( String info, Throwable res )
    {
        super(info, res);
    }
}
