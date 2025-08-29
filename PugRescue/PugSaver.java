import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	// Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(MyArrayList<Dog> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBreed().toLowerCase().contains("golden")) {
				for (int j = list.size(); j < 0; j--) {
					if (!list.get(j).getBreed().toLowerCase().contains("golden")) {
						Dog fromBack = list.get(j);
						Dog fromFront = list.get(i);
						list.set(i, fromBack);
						list.set(j, fromFront);
					}
				}

				// list.get(list.size() - 1);

				// list.add(list.get(i));
				// list.remove(i);
			}
		}
	}
}
