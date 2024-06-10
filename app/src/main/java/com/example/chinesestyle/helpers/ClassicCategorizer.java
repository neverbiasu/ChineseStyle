package com.example.chinesestyle.helpers;

import com.example.chinesestyle.models.Classic;

public class ClassicCategorizer {
    public static String categorize(String title, String author, String content) {
        // 这里使用简单的规则，实际应用中可能需要更复杂的算法或机器学习
        if (title.contains("诗") || author.contains("李白") || author.contains("杜甫")) {
            return Classic.CATEGORY_POETRY;
        } else if (title.contains("词") || author.contains("苏轼") || author.contains("李清照")) {
            return Classic.CATEGORY_CI;
        } else if (title.contains("赋") || content.contains("兮")) {
            return Classic.CATEGORY_FU;
        } else if (title.contains("歌") || title.contains("乐府")) {
            return Classic.CATEGORY_SONG;
        } else {
            return Classic.CATEGORY_OTHER;
        }
    }
}