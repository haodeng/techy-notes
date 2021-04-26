package concurrent;

public class Synchronized {
    // 1. synchronized on instance method, lock on current instance
    public synchronized void instanceLock() {
        // code
    }

    // 2. Same as 1, lock on current instance, but lock the block.
    public void instanceBlockLock() {
        synchronized (this) {
            // code
        }
    }

    // 3. static, lock on Class
    public static synchronized void classLock() {
        // code
    }

    // 4. same as 3, lock on Class, but lock the block
    public void classBlockLock() {
        synchronized (this.getClass()) {
            // code
        }
    }

    // 5. lock on object 0
    public void blockLock() {
        Object o = new Object();
        synchronized (o) {
            // code
        }
    }
}
