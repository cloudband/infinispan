package org.horizon.commands;

import org.horizon.CacheException;
import org.horizon.commands.read.GetKeyValueCommand;
import org.horizon.commands.remote.ReplicateCommand;
import org.horizon.commands.tx.CommitCommand;
import org.horizon.commands.tx.PrepareCommand;
import org.horizon.commands.tx.RollbackCommand;
import org.horizon.commands.write.ClearCommand;
import org.horizon.commands.write.InvalidateCommand;
import org.horizon.commands.write.PutKeyValueCommand;
import org.horizon.commands.write.PutMapCommand;
import org.horizon.commands.write.RemoveCommand;
import org.horizon.commands.write.ReplaceCommand;

/**
 * Specifically used to create un-initialized {@link org.horizon.commands.ReplicableCommand}s from a byte stream.
 *
 * @author Manik Surtani
 * @since 4.0
 */
public class RemoteCommandFactory {

   /**
    * Creates an un-initialized command.  Un-initialized in the sense that parameters will be set, but any components
    * specific to the cache in question will not be set.
    * <p/>
    * You would typically set these parameters using {@link org.horizon.commands.CommandsFactory#initializeReplicableCommand(ReplicableCommand)}
    * <p/>
    *
    * @param id         id of the command
    * @param parameters parameters to set
    * @return a replicable command
    */
   public ReplicableCommand fromStream(byte id, Object[] parameters) {
      ReplicableCommand command;
      switch (id) {
         case PutKeyValueCommand.METHOD_ID:
            command = new PutKeyValueCommand();
            break;
         case PutMapCommand.METHOD_ID:
            command = new PutMapCommand();
            break;
         case RemoveCommand.METHOD_ID:
            command = new RemoveCommand();
            break;
         case ReplaceCommand.METHOD_ID:
            command = new ReplaceCommand();
            break;
         case GetKeyValueCommand.METHOD_ID:
            command = new GetKeyValueCommand();
            break;
         case ClearCommand.METHOD_ID:
            command = new ClearCommand();
            break;
         case PrepareCommand.METHOD_ID:
            command = new PrepareCommand();
            break;
         case CommitCommand.METHOD_ID:
            command = new CommitCommand();
            break;
         case RollbackCommand.METHOD_ID:
            command = new RollbackCommand();
            break;
         case ReplicateCommand.METHOD_ID:
            command = new ReplicateCommand();
            break;
         case InvalidateCommand.METHOD_ID:
            command = new InvalidateCommand();
            break;
         default:
            throw new CacheException("Unknown command id " + id + "!");
      }
      command.setParameters(id, parameters);
      return command;
   }
}
