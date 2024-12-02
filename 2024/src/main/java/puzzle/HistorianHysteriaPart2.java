import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Stream;
import util.FilesUtil;

String INPUT_FILE_PATH = "2024/src/main/resource/HistorianHysteria.txt";
private static final Logger LOGGER = Logger.getLogger("HistorianHysteriaPart2");

void main() {
  long similarityScore = 0;
  Map<Integer, Integer> rightListDuplicates = new HashMap<>();

  List<Integer>[] lists = getLists();
  List<Integer> listLeft = lists[0];
  List<Integer> listRight = lists[1];

  if (listLeft.size() == listRight.size()) {
    listLeft.sort(Integer::compare);
    listRight.sort(Integer::compare);

    LOGGER.info(listLeft.toString());
    LOGGER.info(listRight.toString());

    for (Integer integer : listRight) {
      rightListDuplicates.put(integer, (Objects.isNull(rightListDuplicates.get(integer))? 1 : rightListDuplicates.get(integer) + 1));
    }

    LOGGER.info(rightListDuplicates.toString());

    for (Integer integer : listLeft) {
      similarityScore += Objects.isNull(rightListDuplicates.get(integer))? 0 : (long) integer * rightListDuplicates.get(integer);
    }
  }

  LOGGER.info("Similarity: " + similarityScore);
}

List<Integer>[] getLists() {
  List<Integer> firstColumn = new ArrayList<>();
  List<Integer> secondColumn = new ArrayList<>();

  Stream<String> lines = FilesUtil.loadInputFile(INPUT_FILE_PATH);
  lines.forEach(line -> {
    String[] parts = line.trim().split("\\s+");

    if (parts.length == 2) {
      firstColumn.add(Integer.parseInt(parts[0]));
      secondColumn.add(Integer.parseInt(parts[1]));
    } else {
      throw new RuntimeException("Input needs two columns with integers");
    }
  });

  return new List[]{firstColumn, secondColumn};
}
