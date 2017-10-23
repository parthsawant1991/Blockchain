import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GossipNode {

	private int id;
	private ArrayList<GossipNode> neighbors = new ArrayList<GossipNode>();
	ArrayList<String> incomingMessages = new ArrayList<String>();
	ArrayList<Integer> incomingSender = new ArrayList<Integer>();
	HashMap<String, String> messageHash = new HashMap<String, String>();

	public GossipNode(int _id) {
		this.id = _id;
	}

	public void populateNeighbors(ArrayList<GossipNode> _neighbors) {
		this.neighbors = _neighbors;
	}

	public void sendGossip(String _message) {

		Iterator<GossipNode> it = neighbors.iterator();
		while (it.hasNext()) {
			it.next().receiveGossip(_message, this.id);
		}
	}

	public void receiveGossip(String _message, int _id) {

		String hashMessage = getHashedMessage(_message, _id);

		if (messageHash.containsKey(hashMessage)) {
			System.out.println("Duplicate message received and discarded.");
		} else {
			messageHash.put(hashMessage, _message);
			incomingMessages.add(_message);
			incomingSender.add(_id);
			System.out.println("Message received from " + _id + ": \"" + _message + "\" Hash Code: " + hashMessage);
		}

	}

	private String getHashedMessage(String _message, int _id) {

		byte[] hashCodeMessage = null;
		String s = null;
		_message = _message + _id;

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			hashCodeMessage = digest.digest(_message.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (byte b : hashCodeMessage) {
				sb.append(b);
			}
			return sb.toString();

		} catch (Exception e) {
			System.out.println("Error computing hash");
		}
		return s;

	}

}
