import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	// Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(MyArrayList<Dog> list) {
		if(list == null || list.size() == 0 || list.size() == 1) {
			throw new IllegalArgumentException("List is null bro");
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBreed().toLowerCase().indexOf("golden") != -1) {
				for (int j = list.size() - 1; j > 0; j--) {
					if (list.get(j).getBreed().toLowerCase().indexOf("gold") == -1) {
						if (i < j) {
							Dog fromBack = list.get(j);
							Dog fromFront = list.get(i);
							list.set(i, fromBack);
							list.set(j, fromFront);
						}
					}
				}
			}
		}
	}
}
