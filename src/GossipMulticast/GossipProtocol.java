import java.util.ArrayList;

public class GossipProtocol {

	static ArrayList<GossipNode> members = new ArrayList<GossipNode>();
	static int COUNT = 5;

	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < COUNT; i++) {
			members.add(new GossipNode(i));
		}

		for (GossipNode g : members) {
			g.populateNeighbors(members);
		}

		Thread.sleep(500);
		members.get(0).sendGossip("Hello");
		members.get(3).sendGossip("Hi there");
		members.get(4).sendGossip("Hello");
		members.get(0).sendGossip("Hello");
		members.get(3).sendGossip("Hi there");
	}
}
