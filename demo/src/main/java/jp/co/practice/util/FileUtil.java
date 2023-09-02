package jp.co.practice.util;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ファイル Util
 */
public class FileUtil {

    /**
     * 静的ファイルの絶対パスを取得します。
     * @param staticFilePath 静的ファイルパス
     * @return {@link Path}
     */
    public static Path getAbsoluteFilePath(String... staticFilePath) {
        return Paths.get(PropertyUtil.getApplicationValue("file.root-path"), staticFilePath);
    }
}
