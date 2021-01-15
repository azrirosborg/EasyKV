package dk.anaha.easykv.server.communication;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: easykvevent.proto")
public final class easykveventGrpc {

  private easykveventGrpc() {}

  public static final String SERVICE_NAME = "easykvevent";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykvevent.KeyEvent,
      dk.anaha.easykv.server.communication.Easykvevent.Empty> getEventWatcherMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "eventWatcher",
      requestType = dk.anaha.easykv.server.communication.Easykvevent.KeyEvent.class,
      responseType = dk.anaha.easykv.server.communication.Easykvevent.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykvevent.KeyEvent,
      dk.anaha.easykv.server.communication.Easykvevent.Empty> getEventWatcherMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykvevent.KeyEvent, dk.anaha.easykv.server.communication.Easykvevent.Empty> getEventWatcherMethod;
    if ((getEventWatcherMethod = easykveventGrpc.getEventWatcherMethod) == null) {
      synchronized (easykveventGrpc.class) {
        if ((getEventWatcherMethod = easykveventGrpc.getEventWatcherMethod) == null) {
          easykveventGrpc.getEventWatcherMethod = getEventWatcherMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykvevent.KeyEvent, dk.anaha.easykv.server.communication.Easykvevent.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "eventWatcher"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykvevent.KeyEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykvevent.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new easykveventMethodDescriptorSupplier("eventWatcher"))
              .build();
        }
      }
    }
    return getEventWatcherMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static easykveventStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<easykveventStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<easykveventStub>() {
        @java.lang.Override
        public easykveventStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new easykveventStub(channel, callOptions);
        }
      };
    return easykveventStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static easykveventBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<easykveventBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<easykveventBlockingStub>() {
        @java.lang.Override
        public easykveventBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new easykveventBlockingStub(channel, callOptions);
        }
      };
    return easykveventBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static easykveventFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<easykveventFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<easykveventFutureStub>() {
        @java.lang.Override
        public easykveventFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new easykveventFutureStub(channel, callOptions);
        }
      };
    return easykveventFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class easykveventImplBase implements io.grpc.BindableService {

    /**
     */
    public void eventWatcher(dk.anaha.easykv.server.communication.Easykvevent.KeyEvent request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykvevent.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getEventWatcherMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getEventWatcherMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykvevent.KeyEvent,
                dk.anaha.easykv.server.communication.Easykvevent.Empty>(
                  this, METHODID_EVENT_WATCHER)))
          .build();
    }
  }

  /**
   */
  public static final class easykveventStub extends io.grpc.stub.AbstractAsyncStub<easykveventStub> {
    private easykveventStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected easykveventStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new easykveventStub(channel, callOptions);
    }

    /**
     */
    public void eventWatcher(dk.anaha.easykv.server.communication.Easykvevent.KeyEvent request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykvevent.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEventWatcherMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class easykveventBlockingStub extends io.grpc.stub.AbstractBlockingStub<easykveventBlockingStub> {
    private easykveventBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected easykveventBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new easykveventBlockingStub(channel, callOptions);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykvevent.Empty eventWatcher(dk.anaha.easykv.server.communication.Easykvevent.KeyEvent request) {
      return blockingUnaryCall(
          getChannel(), getEventWatcherMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class easykveventFutureStub extends io.grpc.stub.AbstractFutureStub<easykveventFutureStub> {
    private easykveventFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected easykveventFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new easykveventFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykvevent.Empty> eventWatcher(
        dk.anaha.easykv.server.communication.Easykvevent.KeyEvent request) {
      return futureUnaryCall(
          getChannel().newCall(getEventWatcherMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EVENT_WATCHER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final easykveventImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(easykveventImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EVENT_WATCHER:
          serviceImpl.eventWatcher((dk.anaha.easykv.server.communication.Easykvevent.KeyEvent) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykvevent.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class easykveventBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    easykveventBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dk.anaha.easykv.server.communication.Easykvevent.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("easykvevent");
    }
  }

  private static final class easykveventFileDescriptorSupplier
      extends easykveventBaseDescriptorSupplier {
    easykveventFileDescriptorSupplier() {}
  }

  private static final class easykveventMethodDescriptorSupplier
      extends easykveventBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    easykveventMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (easykveventGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new easykveventFileDescriptorSupplier())
              .addMethod(getEventWatcherMethod())
              .build();
        }
      }
    }
    return result;
  }
}
