package io.evercam.network;

import io.evercam.network.discovery.DiscoveredCamera;
import io.evercam.network.discovery.NetworkInfo;
import io.evercam.network.discovery.ScanRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main
{
	private static final String ARG_IP = "-ip";
	private static final String ARG_SUBNET_MASK = "-m";
	
	/**
	 * Discover all cameras in local network and print them in console
	 * 
	 * @param args
	 *            pass parameter -v/--verbose to enable verbose logging
	 */
	public static void main(String[] args)
	{
		String ip = "192.168.1.1";
		String subnetMask = "255.255.255.0";
		System.out.println("Hello World!");
		if (args.length > 0)
		{
			List<String> argsArray = Arrays.asList(args);
			if(argsArray.contains("-v") || argsArray.contains("--verbose"))
			{
				Constants.ENABLE_LOGGING = true;
			}
			
			if(argsArray.contains(ARG_IP) && argsArray.contains(ARG_SUBNET_MASK))
			{
				int ipIndex = argsArray.indexOf(ARG_IP) + 1;
				int subnetIndex = argsArray.indexOf(ARG_SUBNET_MASK) + 1;
				
				ip = argsArray.get(ipIndex);
				subnetMask = argsArray.get(subnetIndex);
			}
		}

		if(ip.isEmpty()) ip = NetworkInfo.getLinuxRouterIp();
		if(subnetMask.isEmpty()) subnetMask = NetworkInfo.getLinuxSubnetMask();

		// String deviceIp = "";
		// String subnetMask = "";
		// try
		// {
		// NetworkInterface networkInterface =
		// NetworkInfo.getNetworkInterfaceByName("wlan0");
		// deviceIp = NetworkInfo.getIpFromInterface(networkInterface);
		// subnetMask =
		// IpTranslator.cidrToMask(NetworkInfo.getCidrFromInterface(networkInterface));
		// }
		// catch (Exception e)
		// {
		// // TODO: handle exception
		// }
		EvercamDiscover.printLogMessage("Router IP address: " + ip + " subnet mask: "
				+ subnetMask);
		System.out.println("Router IP address: " + ip + " subnet mask: "
				+ subnetMask);
		EvercamDiscover.printLogMessage("Scanning...");

		try
		{
			ScanRange scanRange = new ScanRange(ip, subnetMask);
			System.out.println("scanning");
			ArrayList<DiscoveredCamera> cameraList = new EvercamDiscover().withDefaults(true)
					.discoverAllLinux(scanRange);
			// cameraList.add(new DiscoveredCamera("203.134.200.170"));
			System.out.println("scanned");
			EvercamDiscover.printLogMessage("Scanning finished, found " + cameraList.size()
					+ " cameras");

			printAsJson(cameraList);

			EvercamDiscover.printLogMessage("On normal completion: 0");
			System.exit(0);
		}
		catch (Exception e)
		{
			if(Constants.ENABLE_LOGGING)
			{
				e.printStackTrace();
			}
			EvercamDiscover.printLogMessage("On error: 1");
			System.exit(1);
		}
	}

	public static void printAsJson(ArrayList<DiscoveredCamera> cameraList)
	{
		if (cameraList != null)
		{
			JSONArray jsonArray = new JSONArray();

			for (DiscoveredCamera camera : cameraList)
			{
				JSONObject jsonObject = camera.toJsonObject();
				jsonArray.put(jsonObject);
			}

			JSONObject arrayJsonObject = new JSONObject().put("cameras", jsonArray);

			System.out.println(arrayJsonObject.toString(4));
		}
	}
}
