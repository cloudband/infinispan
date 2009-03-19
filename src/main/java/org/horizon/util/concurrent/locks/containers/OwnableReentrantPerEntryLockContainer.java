package org.horizon.util.concurrent.locks.containers;

import org.horizon.invocation.InvocationContextContainer;
import org.horizon.util.concurrent.locks.OwnableReentrantLock;

import java.util.concurrent.locks.Lock;

/**
 * // TODO: Manik: Document this!
 *
 * @author Manik Surtani
 * @since 4.0
 */
public class OwnableReentrantPerEntryLockContainer extends AbstractPerEntryLockContainer {

   private InvocationContextContainer icc;

   public OwnableReentrantPerEntryLockContainer(int concurrencyLevel, InvocationContextContainer icc) {
      super(concurrencyLevel);
      this.icc = icc;
   }

   protected Lock newLock() {
      return new OwnableReentrantLock(icc);
   }

   public boolean ownsLock(Object key, Object owner) {
      OwnableReentrantLock l = getLockFromMap(key);
      return l != null && owner.equals(l.getOwner());
   }

   public boolean isLocked(Object key) {
      OwnableReentrantLock l = getLockFromMap(key);
      return l != null && l.isLocked();
   }

   private OwnableReentrantLock getLockFromMap(Object key) {
      return (OwnableReentrantLock) locks.get(key);
   }
}
