// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ServiceContract.proto

package forum;

public final class ServiceContract {
  private ServiceContract() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_forum_SubscribeUnSubscribe_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_forum_SubscribeUnSubscribe_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_forum_ExistingTopics_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_forum_ExistingTopics_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_forum_ForumMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_forum_ForumMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025ServiceContract.proto\022\005forum\032\033google/p" +
      "rotobuf/empty.proto\":\n\024SubscribeUnSubscr" +
      "ibe\022\017\n\007usrName\030\001 \001(\t\022\021\n\ttopicName\030\002 \001(\t\"" +
      "#\n\016ExistingTopics\022\021\n\ttopicName\030\001 \003(\t\"C\n\014" +
      "ForumMessage\022\020\n\010fromUser\030\001 \001(\t\022\021\n\ttopicN" +
      "ame\030\002 \001(\t\022\016\n\006txtMsg\030\003 \001(\t2\224\002\n\005Forum\022D\n\016t" +
      "opicSubscribe\022\033.forum.SubscribeUnSubscri" +
      "be\032\023.forum.ForumMessage0\001\022G\n\020topicUnSubs" +
      "cribe\022\033.forum.SubscribeUnSubscribe\032\026.goo" +
      "gle.protobuf.Empty\022=\n\014getAllTopics\022\026.goo" +
      "gle.protobuf.Empty\032\025.forum.ExistingTopic" +
      "s\022=\n\016publishMessage\022\023.forum.ForumMessage" +
      "\032\026.google.protobuf.EmptyB\t\n\005forumP\001b\006pro" +
      "to3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.EmptyProto.getDescriptor(),
        });
    internal_static_forum_SubscribeUnSubscribe_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_forum_SubscribeUnSubscribe_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_forum_SubscribeUnSubscribe_descriptor,
        new java.lang.String[] { "UsrName", "TopicName", });
    internal_static_forum_ExistingTopics_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_forum_ExistingTopics_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_forum_ExistingTopics_descriptor,
        new java.lang.String[] { "TopicName", });
    internal_static_forum_ForumMessage_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_forum_ForumMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_forum_ForumMessage_descriptor,
        new java.lang.String[] { "FromUser", "TopicName", "TxtMsg", });
    com.google.protobuf.EmptyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
