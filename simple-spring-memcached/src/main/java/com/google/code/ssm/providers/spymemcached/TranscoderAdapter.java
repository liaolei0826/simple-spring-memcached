/*
 * Copyright (c) 2010-2011 Jakub Białek
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.google.code.ssm.providers.spymemcached;

import com.google.code.ssm.providers.CacheTranscoder;
import com.google.code.ssm.providers.CachedObject;

import net.spy.memcached.CachedData;
import net.spy.memcached.transcoders.Transcoder;

/**
 * 
 * @author Jakub Białek
 * @since 2.0.0
 * 
 * @param <T>
 */
class TranscoderAdapter<T> implements Transcoder<T> {

    private CacheTranscoder<T> transcoder;

    TranscoderAdapter(CacheTranscoder<T> transcoder) {
        this.transcoder = transcoder;
    }

    @Override
    public boolean asyncDecode(CachedData d) {
        return false;
    }

    @Override
    public T decode(CachedData d) {
        return (T) transcoder.decode(new CachedObjectWrapper(d));
    }

    @Override
    public CachedData encode(T o) {
        CachedObject cachedObject = transcoder.encode(o);
        return new CachedData(cachedObject.getFlags(), cachedObject.getData(), CachedObject.MAX_SIZE);
    }

    @Override
    public int getMaxSize() {
        return CachedObject.MAX_SIZE;
    }

}
