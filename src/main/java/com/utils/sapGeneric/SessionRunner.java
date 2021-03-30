package com.utils.sapGeneric;

import com.jacob.activeX.ActiveXComponent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionRunner  {

    protected static volatile Map<Long, ActiveXComponent> sessions = new ConcurrentHashMap();

    public static ActiveXComponent getCurrentSession() {
        return  sessions.isEmpty() ||
                !sessions.containsKey(Thread.currentThread().getId())? null : sessions.get(Thread.currentThread().getId());
    }

    public static Map<Long, ActiveXComponent> getSessions() {
        return sessions;
    }
}
