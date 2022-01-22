import java.io.*;
import java.util.*;
import io.evercam.network.*;
import io.evercam.network.discovery.*;
// import src.io.evercam.discovery.*;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!"); 	// Prints Hello World! to the console.
        EvercamDiscover evercamDiscover = new EvercamDiscover()
				// .withThumbnail(true) //Include thumbnail URL or not
				.withDefaults(true); //Include camera defaults or not

//Discover all cameras
        ScanRange scanRange = new ScanRange("192.168.1.1", //router IP
                                    "255.255.255.0"); //subnet mask
        // ArrayList<DiscoveredCamera> cameraList = evercamDiscover.discoverAllAndroid(scanRange);
        ArrayList<DiscoveredCamera> cameraList = evercamDiscover.discoverAllLinux(scanRange);
        
    }
}

// EvercamDiscover evercamDiscover = new EvercamDiscover()
// 				.withThumbnail(true) //Include thumbnail URL or not
// 				.withDefaults(true); //Include camera defaults or not

// //Discover all cameras
// ScanRange scanRange = new ScanRange("192.168.1.1", //router IP
//                                     "255.255.255.0"); //subnet mask
// ArrayList<DiscoveredCameras> cameraList = evercamDiscover.discoverAllAndroid(scanRange);