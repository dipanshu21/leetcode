package Easy;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public static void main(String[] args) {
        List<String> ranges = new SummaryRanges().summaryRanges(new int[]{0, 1, 2});
        printList(ranges);
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>(nums.length);

        if (nums == null || nums.length == 0) {
            return ranges;
        }

        final String multiRange = "%d->%d";
        final String singleRange = "%d";

        int lastRangeStartIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] != 1) {
                if (lastRangeStartIndex == (i - 1)) {
                    ranges.add(String.format(singleRange, nums[lastRangeStartIndex]));
                } else {
                    ranges.add(String.format(multiRange, nums[lastRangeStartIndex], nums[i - 1]));
                }
                lastRangeStartIndex = i;
            }
        }

        if (lastRangeStartIndex == (nums.length - 1)) {
            ranges.add(String.format(singleRange, nums[lastRangeStartIndex]));
        } else {
            ranges.add(String.format(multiRange, nums[lastRangeStartIndex], nums[nums.length - 1]));
        }

        return ranges;
    }

    private static void printList(List<String> list) {
        for(String s: list) {
            System.out.println(s);
        }
    }
}
