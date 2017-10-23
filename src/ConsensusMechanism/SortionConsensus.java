import java.util.ArrayList;
import java.util.HashMap;

public class SortionConsensus {

	public static void main(String args[]) {

		// generate participants
		ArrayList<Participant> nodes = new ArrayList<Participant>();

		for (int i = 1; i <= 25; i++) {
			nodes.add(new Participant(i));
		}

		// generate cut-off threshold
		int cutoff = Math.abs(((int)System.nanoTime())% 15);
		System.out.println("Cut-off: " + cutoff);

		// generate list of selected parties and elect leader and verifier group
		HashMap<Participant, Integer> sv = new HashMap<Participant, Integer>();

		for (Participant p : nodes) {
			int vote = p.vote();
			System.out.println(vote);
			if (vote <= cutoff)
				sv.put(p, vote);
		}

		int leadervote = Integer.MAX_VALUE;
		Participant leader = new Participant();

		for (Participant v : sv.keySet()) {

			System.out.println("SV member: " + v.getIndex() + " vote: " + sv.get(v));
			if (sv.get(v) <= leadervote)
				leader = v;
			leadervote = sv.get(v);
		}
		
		if(sv.isEmpty()) {
			System.out.println("Re-run step");
			return;
		}

		System.out.println("From " + sv.size() + " Leader is " + leader.getIndex() + " with a vote of " + leadervote
				+ " for a cut-off of " + cutoff);

	}

}
