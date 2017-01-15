package util;

public class NetworkProxy
{
    public void init()
    {
        System.getProperties().put("proxySet", "true");
        System.getProperties()
                .put("proxyHost", "euwebproxy.wlb.eur.nsroot.net");
        System.getProperties().put("proxyPort", "80");
    }
}
