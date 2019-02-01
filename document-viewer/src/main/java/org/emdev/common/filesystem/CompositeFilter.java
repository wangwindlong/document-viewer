package org.emdev.common.filesystem;

import android.util.Log;

import java.io.File;
import java.io.FileFilter;

public class CompositeFilter implements FileFilter {

    final boolean acceptAll;

    final FileFilter[] fileFilters;

    public CompositeFilter(final boolean acceptAll, final FileFilter... fileFilters) {
        this.acceptAll = acceptAll;
        this.fileFilters = fileFilters;
    }

    @Override
    public boolean accept(final File file) {
        boolean res = false;
        if (acceptAll) {
            res = true;
            for (final FileFilter f : fileFilters) {
                res = res && f.accept(file);
                if (!res) {
                    break;
                }
            }
        } else {
            for (final FileFilter f : fileFilters) {
                boolean accept = f.accept(file);
                res = res || accept;
                if (res) {
                    break;
                }
            }
        }
        return res;
    }
}
