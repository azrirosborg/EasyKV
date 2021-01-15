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
    comments = "Source: easykv.proto")
public final class easykvGrpc {

  private easykvGrpc() {}

  public static final String SERVICE_NAME = "easykv";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.ClientId,
      dk.anaha.easykv.server.communication.Easykv.Bool> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "register",
      requestType = dk.anaha.easykv.server.communication.Easykv.ClientId.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.ClientId,
      dk.anaha.easykv.server.communication.Easykv.Bool> getRegisterMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.ClientId, dk.anaha.easykv.server.communication.Easykv.Bool> getRegisterMethod;
    if ((getRegisterMethod = easykvGrpc.getRegisterMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getRegisterMethod = easykvGrpc.getRegisterMethod) == null) {
          easykvGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.ClientId, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.ClientId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("register"))
              .build();
        }
      }
    }
    return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.Value> getReadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "read",
      requestType = dk.anaha.easykv.server.communication.Easykv.Key.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Value.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.Value> getReadMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.Value> getReadMethod;
    if ((getReadMethod = easykvGrpc.getReadMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getReadMethod = easykvGrpc.getReadMethod) == null) {
          easykvGrpc.getReadMethod = getReadMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.Value>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "read"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Value.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("read"))
              .build();
        }
      }
    }
    return getReadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyValue,
      dk.anaha.easykv.server.communication.Easykv.Bool> getWriteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "write",
      requestType = dk.anaha.easykv.server.communication.Easykv.KeyValue.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyValue,
      dk.anaha.easykv.server.communication.Easykv.Bool> getWriteMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyValue, dk.anaha.easykv.server.communication.Easykv.Bool> getWriteMethod;
    if ((getWriteMethod = easykvGrpc.getWriteMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getWriteMethod = easykvGrpc.getWriteMethod) == null) {
          easykvGrpc.getWriteMethod = getWriteMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.KeyValue, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "write"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.KeyValue.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("write"))
              .build();
        }
      }
    }
    return getWriteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.Bool> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = dk.anaha.easykv.server.communication.Easykv.Key.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.Bool> getDeleteMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.Bool> getDeleteMethod;
    if ((getDeleteMethod = easykvGrpc.getDeleteMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getDeleteMethod = easykvGrpc.getDeleteMethod) == null) {
          easykvGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.Bool> getExistMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "exist",
      requestType = dk.anaha.easykv.server.communication.Easykv.Key.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.Bool> getExistMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.Bool> getExistMethod;
    if ((getExistMethod = easykvGrpc.getExistMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getExistMethod = easykvGrpc.getExistMethod) == null) {
          easykvGrpc.getExistMethod = getExistMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "exist"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("exist"))
              .build();
        }
      }
    }
    return getExistMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.Keys> getListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "list",
      requestType = dk.anaha.easykv.server.communication.Easykv.Key.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Keys.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.Keys> getListMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.Keys> getListMethod;
    if ((getListMethod = easykvGrpc.getListMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getListMethod = easykvGrpc.getListMethod) == null) {
          easykvGrpc.getListMethod = getListMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.Keys>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "list"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Keys.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("list"))
              .build();
        }
      }
    }
    return getListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyString,
      dk.anaha.easykv.server.communication.Easykv.Bool> getLpushMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "lpush",
      requestType = dk.anaha.easykv.server.communication.Easykv.KeyString.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyString,
      dk.anaha.easykv.server.communication.Easykv.Bool> getLpushMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyString, dk.anaha.easykv.server.communication.Easykv.Bool> getLpushMethod;
    if ((getLpushMethod = easykvGrpc.getLpushMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getLpushMethod = easykvGrpc.getLpushMethod) == null) {
          easykvGrpc.getLpushMethod = getLpushMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.KeyString, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "lpush"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.KeyString.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("lpush"))
              .build();
        }
      }
    }
    return getLpushMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyString,
      dk.anaha.easykv.server.communication.Easykv.Bool> getRpushMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rpush",
      requestType = dk.anaha.easykv.server.communication.Easykv.KeyString.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyString,
      dk.anaha.easykv.server.communication.Easykv.Bool> getRpushMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyString, dk.anaha.easykv.server.communication.Easykv.Bool> getRpushMethod;
    if ((getRpushMethod = easykvGrpc.getRpushMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getRpushMethod = easykvGrpc.getRpushMethod) == null) {
          easykvGrpc.getRpushMethod = getRpushMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.KeyString, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "rpush"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.KeyString.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("rpush"))
              .build();
        }
      }
    }
    return getRpushMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.StringValue> getLpopMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "lpop",
      requestType = dk.anaha.easykv.server.communication.Easykv.Key.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.StringValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.StringValue> getLpopMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.StringValue> getLpopMethod;
    if ((getLpopMethod = easykvGrpc.getLpopMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getLpopMethod = easykvGrpc.getLpopMethod) == null) {
          easykvGrpc.getLpopMethod = getLpopMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.StringValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "lpop"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.StringValue.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("lpop"))
              .build();
        }
      }
    }
    return getLpopMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.StringValue> getRpopMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rpop",
      requestType = dk.anaha.easykv.server.communication.Easykv.Key.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.StringValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.StringValue> getRpopMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.StringValue> getRpopMethod;
    if ((getRpopMethod = easykvGrpc.getRpopMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getRpopMethod = easykvGrpc.getRpopMethod) == null) {
          easykvGrpc.getRpopMethod = getRpopMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.StringValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "rpop"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.StringValue.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("rpop"))
              .build();
        }
      }
    }
    return getRpopMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyTrim,
      dk.anaha.easykv.server.communication.Easykv.Bool> getLtrimMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ltrim",
      requestType = dk.anaha.easykv.server.communication.Easykv.KeyTrim.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyTrim,
      dk.anaha.easykv.server.communication.Easykv.Bool> getLtrimMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyTrim, dk.anaha.easykv.server.communication.Easykv.Bool> getLtrimMethod;
    if ((getLtrimMethod = easykvGrpc.getLtrimMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getLtrimMethod = easykvGrpc.getLtrimMethod) == null) {
          easykvGrpc.getLtrimMethod = getLtrimMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.KeyTrim, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ltrim"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.KeyTrim.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("ltrim"))
              .build();
        }
      }
    }
    return getLtrimMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.IntValue> getLlenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "llen",
      requestType = dk.anaha.easykv.server.communication.Easykv.Key.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.IntValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.IntValue> getLlenMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.IntValue> getLlenMethod;
    if ((getLlenMethod = easykvGrpc.getLlenMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getLlenMethod = easykvGrpc.getLlenMethod) == null) {
          easykvGrpc.getLlenMethod = getLlenMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.IntValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "llen"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.IntValue.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("llen"))
              .build();
        }
      }
    }
    return getLlenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyString,
      dk.anaha.easykv.server.communication.Easykv.Bool> getLcontainsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "lcontains",
      requestType = dk.anaha.easykv.server.communication.Easykv.KeyString.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyString,
      dk.anaha.easykv.server.communication.Easykv.Bool> getLcontainsMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyString, dk.anaha.easykv.server.communication.Easykv.Bool> getLcontainsMethod;
    if ((getLcontainsMethod = easykvGrpc.getLcontainsMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getLcontainsMethod = easykvGrpc.getLcontainsMethod) == null) {
          easykvGrpc.getLcontainsMethod = getLcontainsMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.KeyString, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "lcontains"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.KeyString.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("lcontains"))
              .build();
        }
      }
    }
    return getLcontainsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.Strings> getLgetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "lget",
      requestType = dk.anaha.easykv.server.communication.Easykv.Key.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Strings.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.Strings> getLgetMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.Strings> getLgetMethod;
    if ((getLgetMethod = easykvGrpc.getLgetMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getLgetMethod = easykvGrpc.getLgetMethod) == null) {
          easykvGrpc.getLgetMethod = getLgetMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.Strings>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "lget"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Strings.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("lget"))
              .build();
        }
      }
    }
    return getLgetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong,
      dk.anaha.easykv.server.communication.Easykv.LongValue> getIncrbyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "incrby",
      requestType = dk.anaha.easykv.server.communication.Easykv.KeyLong.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.LongValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong,
      dk.anaha.easykv.server.communication.Easykv.LongValue> getIncrbyMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong, dk.anaha.easykv.server.communication.Easykv.LongValue> getIncrbyMethod;
    if ((getIncrbyMethod = easykvGrpc.getIncrbyMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getIncrbyMethod = easykvGrpc.getIncrbyMethod) == null) {
          easykvGrpc.getIncrbyMethod = getIncrbyMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.KeyLong, dk.anaha.easykv.server.communication.Easykv.LongValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "incrby"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.KeyLong.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.LongValue.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("incrby"))
              .build();
        }
      }
    }
    return getIncrbyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong,
      dk.anaha.easykv.server.communication.Easykv.LongValue> getDecrbyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "decrby",
      requestType = dk.anaha.easykv.server.communication.Easykv.KeyLong.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.LongValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong,
      dk.anaha.easykv.server.communication.Easykv.LongValue> getDecrbyMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong, dk.anaha.easykv.server.communication.Easykv.LongValue> getDecrbyMethod;
    if ((getDecrbyMethod = easykvGrpc.getDecrbyMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getDecrbyMethod = easykvGrpc.getDecrbyMethod) == null) {
          easykvGrpc.getDecrbyMethod = getDecrbyMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.KeyLong, dk.anaha.easykv.server.communication.Easykv.LongValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "decrby"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.KeyLong.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.LongValue.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("decrby"))
              .build();
        }
      }
    }
    return getDecrbyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong,
      dk.anaha.easykv.server.communication.Easykv.Bool> getExpireMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "expire",
      requestType = dk.anaha.easykv.server.communication.Easykv.KeyLong.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong,
      dk.anaha.easykv.server.communication.Easykv.Bool> getExpireMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong, dk.anaha.easykv.server.communication.Easykv.Bool> getExpireMethod;
    if ((getExpireMethod = easykvGrpc.getExpireMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getExpireMethod = easykvGrpc.getExpireMethod) == null) {
          easykvGrpc.getExpireMethod = getExpireMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.KeyLong, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "expire"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.KeyLong.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("expire"))
              .build();
        }
      }
    }
    return getExpireMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong,
      dk.anaha.easykv.server.communication.Easykv.Bool> getExpireatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "expireat",
      requestType = dk.anaha.easykv.server.communication.Easykv.KeyLong.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong,
      dk.anaha.easykv.server.communication.Easykv.Bool> getExpireatMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyLong, dk.anaha.easykv.server.communication.Easykv.Bool> getExpireatMethod;
    if ((getExpireatMethod = easykvGrpc.getExpireatMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getExpireatMethod = easykvGrpc.getExpireatMethod) == null) {
          easykvGrpc.getExpireatMethod = getExpireatMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.KeyLong, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "expireat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.KeyLong.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("expireat"))
              .build();
        }
      }
    }
    return getExpireatMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.LongValue> getTtlMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ttl",
      requestType = dk.anaha.easykv.server.communication.Easykv.Key.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.LongValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key,
      dk.anaha.easykv.server.communication.Easykv.LongValue> getTtlMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.LongValue> getTtlMethod;
    if ((getTtlMethod = easykvGrpc.getTtlMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getTtlMethod = easykvGrpc.getTtlMethod) == null) {
          easykvGrpc.getTtlMethod = getTtlMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.Key, dk.anaha.easykv.server.communication.Easykv.LongValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ttl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.LongValue.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("ttl"))
              .build();
        }
      }
    }
    return getTtlMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyWatcher,
      dk.anaha.easykv.server.communication.Easykv.Bool> getWatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "watch",
      requestType = dk.anaha.easykv.server.communication.Easykv.KeyWatcher.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyWatcher,
      dk.anaha.easykv.server.communication.Easykv.Bool> getWatchMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyWatcher, dk.anaha.easykv.server.communication.Easykv.Bool> getWatchMethod;
    if ((getWatchMethod = easykvGrpc.getWatchMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getWatchMethod = easykvGrpc.getWatchMethod) == null) {
          easykvGrpc.getWatchMethod = getWatchMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.KeyWatcher, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "watch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.KeyWatcher.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("watch"))
              .build();
        }
      }
    }
    return getWatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyWatcher,
      dk.anaha.easykv.server.communication.Easykv.Bool> getUnwatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "unwatch",
      requestType = dk.anaha.easykv.server.communication.Easykv.KeyWatcher.class,
      responseType = dk.anaha.easykv.server.communication.Easykv.Bool.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyWatcher,
      dk.anaha.easykv.server.communication.Easykv.Bool> getUnwatchMethod() {
    io.grpc.MethodDescriptor<dk.anaha.easykv.server.communication.Easykv.KeyWatcher, dk.anaha.easykv.server.communication.Easykv.Bool> getUnwatchMethod;
    if ((getUnwatchMethod = easykvGrpc.getUnwatchMethod) == null) {
      synchronized (easykvGrpc.class) {
        if ((getUnwatchMethod = easykvGrpc.getUnwatchMethod) == null) {
          easykvGrpc.getUnwatchMethod = getUnwatchMethod =
              io.grpc.MethodDescriptor.<dk.anaha.easykv.server.communication.Easykv.KeyWatcher, dk.anaha.easykv.server.communication.Easykv.Bool>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "unwatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.KeyWatcher.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.anaha.easykv.server.communication.Easykv.Bool.getDefaultInstance()))
              .setSchemaDescriptor(new easykvMethodDescriptorSupplier("unwatch"))
              .build();
        }
      }
    }
    return getUnwatchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static easykvStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<easykvStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<easykvStub>() {
        @java.lang.Override
        public easykvStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new easykvStub(channel, callOptions);
        }
      };
    return easykvStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static easykvBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<easykvBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<easykvBlockingStub>() {
        @java.lang.Override
        public easykvBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new easykvBlockingStub(channel, callOptions);
        }
      };
    return easykvBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static easykvFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<easykvFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<easykvFutureStub>() {
        @java.lang.Override
        public easykvFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new easykvFutureStub(channel, callOptions);
        }
      };
    return easykvFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class easykvImplBase implements io.grpc.BindableService {

    /**
     */
    public void register(dk.anaha.easykv.server.communication.Easykv.ClientId request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void read(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Value> responseObserver) {
      asyncUnimplementedUnaryCall(getReadMethod(), responseObserver);
    }

    /**
     */
    public void write(dk.anaha.easykv.server.communication.Easykv.KeyValue request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getWriteMethod(), responseObserver);
    }

    /**
     */
    public void delete(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    public void exist(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getExistMethod(), responseObserver);
    }

    /**
     */
    public void list(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Keys> responseObserver) {
      asyncUnimplementedUnaryCall(getListMethod(), responseObserver);
    }

    /**
     */
    public void lpush(dk.anaha.easykv.server.communication.Easykv.KeyString request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getLpushMethod(), responseObserver);
    }

    /**
     */
    public void rpush(dk.anaha.easykv.server.communication.Easykv.KeyString request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getRpushMethod(), responseObserver);
    }

    /**
     */
    public void lpop(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.StringValue> responseObserver) {
      asyncUnimplementedUnaryCall(getLpopMethod(), responseObserver);
    }

    /**
     */
    public void rpop(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.StringValue> responseObserver) {
      asyncUnimplementedUnaryCall(getRpopMethod(), responseObserver);
    }

    /**
     */
    public void ltrim(dk.anaha.easykv.server.communication.Easykv.KeyTrim request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getLtrimMethod(), responseObserver);
    }

    /**
     */
    public void llen(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.IntValue> responseObserver) {
      asyncUnimplementedUnaryCall(getLlenMethod(), responseObserver);
    }

    /**
     */
    public void lcontains(dk.anaha.easykv.server.communication.Easykv.KeyString request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getLcontainsMethod(), responseObserver);
    }

    /**
     */
    public void lget(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Strings> responseObserver) {
      asyncUnimplementedUnaryCall(getLgetMethod(), responseObserver);
    }

    /**
     */
    public void incrby(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue> responseObserver) {
      asyncUnimplementedUnaryCall(getIncrbyMethod(), responseObserver);
    }

    /**
     */
    public void decrby(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue> responseObserver) {
      asyncUnimplementedUnaryCall(getDecrbyMethod(), responseObserver);
    }

    /**
     */
    public void expire(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getExpireMethod(), responseObserver);
    }

    /**
     */
    public void expireat(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getExpireatMethod(), responseObserver);
    }

    /**
     */
    public void ttl(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue> responseObserver) {
      asyncUnimplementedUnaryCall(getTtlMethod(), responseObserver);
    }

    /**
     */
    public void watch(dk.anaha.easykv.server.communication.Easykv.KeyWatcher request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getWatchMethod(), responseObserver);
    }

    /**
     */
    public void unwatch(dk.anaha.easykv.server.communication.Easykv.KeyWatcher request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnimplementedUnaryCall(getUnwatchMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.ClientId,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getReadMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.Key,
                dk.anaha.easykv.server.communication.Easykv.Value>(
                  this, METHODID_READ)))
          .addMethod(
            getWriteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.KeyValue,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_WRITE)))
          .addMethod(
            getDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.Key,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_DELETE)))
          .addMethod(
            getExistMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.Key,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_EXIST)))
          .addMethod(
            getListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.Key,
                dk.anaha.easykv.server.communication.Easykv.Keys>(
                  this, METHODID_LIST)))
          .addMethod(
            getLpushMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.KeyString,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_LPUSH)))
          .addMethod(
            getRpushMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.KeyString,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_RPUSH)))
          .addMethod(
            getLpopMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.Key,
                dk.anaha.easykv.server.communication.Easykv.StringValue>(
                  this, METHODID_LPOP)))
          .addMethod(
            getRpopMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.Key,
                dk.anaha.easykv.server.communication.Easykv.StringValue>(
                  this, METHODID_RPOP)))
          .addMethod(
            getLtrimMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.KeyTrim,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_LTRIM)))
          .addMethod(
            getLlenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.Key,
                dk.anaha.easykv.server.communication.Easykv.IntValue>(
                  this, METHODID_LLEN)))
          .addMethod(
            getLcontainsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.KeyString,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_LCONTAINS)))
          .addMethod(
            getLgetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.Key,
                dk.anaha.easykv.server.communication.Easykv.Strings>(
                  this, METHODID_LGET)))
          .addMethod(
            getIncrbyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.KeyLong,
                dk.anaha.easykv.server.communication.Easykv.LongValue>(
                  this, METHODID_INCRBY)))
          .addMethod(
            getDecrbyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.KeyLong,
                dk.anaha.easykv.server.communication.Easykv.LongValue>(
                  this, METHODID_DECRBY)))
          .addMethod(
            getExpireMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.KeyLong,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_EXPIRE)))
          .addMethod(
            getExpireatMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.KeyLong,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_EXPIREAT)))
          .addMethod(
            getTtlMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.Key,
                dk.anaha.easykv.server.communication.Easykv.LongValue>(
                  this, METHODID_TTL)))
          .addMethod(
            getWatchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.KeyWatcher,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_WATCH)))
          .addMethod(
            getUnwatchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.anaha.easykv.server.communication.Easykv.KeyWatcher,
                dk.anaha.easykv.server.communication.Easykv.Bool>(
                  this, METHODID_UNWATCH)))
          .build();
    }
  }

  /**
   */
  public static final class easykvStub extends io.grpc.stub.AbstractAsyncStub<easykvStub> {
    private easykvStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected easykvStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new easykvStub(channel, callOptions);
    }

    /**
     */
    public void register(dk.anaha.easykv.server.communication.Easykv.ClientId request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void read(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Value> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void write(dk.anaha.easykv.server.communication.Easykv.KeyValue request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWriteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exist(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExistMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void list(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Keys> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lpush(dk.anaha.easykv.server.communication.Easykv.KeyString request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLpushMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rpush(dk.anaha.easykv.server.communication.Easykv.KeyString request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRpushMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lpop(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.StringValue> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLpopMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rpop(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.StringValue> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRpopMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ltrim(dk.anaha.easykv.server.communication.Easykv.KeyTrim request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLtrimMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void llen(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.IntValue> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLlenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lcontains(dk.anaha.easykv.server.communication.Easykv.KeyString request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLcontainsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lget(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Strings> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLgetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void incrby(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIncrbyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void decrby(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDecrbyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void expire(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExpireMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void expireat(dk.anaha.easykv.server.communication.Easykv.KeyLong request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExpireatMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ttl(dk.anaha.easykv.server.communication.Easykv.Key request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTtlMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void watch(dk.anaha.easykv.server.communication.Easykv.KeyWatcher request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unwatch(dk.anaha.easykv.server.communication.Easykv.KeyWatcher request,
        io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnwatchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class easykvBlockingStub extends io.grpc.stub.AbstractBlockingStub<easykvBlockingStub> {
    private easykvBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected easykvBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new easykvBlockingStub(channel, callOptions);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool register(dk.anaha.easykv.server.communication.Easykv.ClientId request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Value read(dk.anaha.easykv.server.communication.Easykv.Key request) {
      return blockingUnaryCall(
          getChannel(), getReadMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool write(dk.anaha.easykv.server.communication.Easykv.KeyValue request) {
      return blockingUnaryCall(
          getChannel(), getWriteMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool delete(dk.anaha.easykv.server.communication.Easykv.Key request) {
      return blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool exist(dk.anaha.easykv.server.communication.Easykv.Key request) {
      return blockingUnaryCall(
          getChannel(), getExistMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Keys list(dk.anaha.easykv.server.communication.Easykv.Key request) {
      return blockingUnaryCall(
          getChannel(), getListMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool lpush(dk.anaha.easykv.server.communication.Easykv.KeyString request) {
      return blockingUnaryCall(
          getChannel(), getLpushMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool rpush(dk.anaha.easykv.server.communication.Easykv.KeyString request) {
      return blockingUnaryCall(
          getChannel(), getRpushMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.StringValue lpop(dk.anaha.easykv.server.communication.Easykv.Key request) {
      return blockingUnaryCall(
          getChannel(), getLpopMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.StringValue rpop(dk.anaha.easykv.server.communication.Easykv.Key request) {
      return blockingUnaryCall(
          getChannel(), getRpopMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool ltrim(dk.anaha.easykv.server.communication.Easykv.KeyTrim request) {
      return blockingUnaryCall(
          getChannel(), getLtrimMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.IntValue llen(dk.anaha.easykv.server.communication.Easykv.Key request) {
      return blockingUnaryCall(
          getChannel(), getLlenMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool lcontains(dk.anaha.easykv.server.communication.Easykv.KeyString request) {
      return blockingUnaryCall(
          getChannel(), getLcontainsMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Strings lget(dk.anaha.easykv.server.communication.Easykv.Key request) {
      return blockingUnaryCall(
          getChannel(), getLgetMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.LongValue incrby(dk.anaha.easykv.server.communication.Easykv.KeyLong request) {
      return blockingUnaryCall(
          getChannel(), getIncrbyMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.LongValue decrby(dk.anaha.easykv.server.communication.Easykv.KeyLong request) {
      return blockingUnaryCall(
          getChannel(), getDecrbyMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool expire(dk.anaha.easykv.server.communication.Easykv.KeyLong request) {
      return blockingUnaryCall(
          getChannel(), getExpireMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool expireat(dk.anaha.easykv.server.communication.Easykv.KeyLong request) {
      return blockingUnaryCall(
          getChannel(), getExpireatMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.LongValue ttl(dk.anaha.easykv.server.communication.Easykv.Key request) {
      return blockingUnaryCall(
          getChannel(), getTtlMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool watch(dk.anaha.easykv.server.communication.Easykv.KeyWatcher request) {
      return blockingUnaryCall(
          getChannel(), getWatchMethod(), getCallOptions(), request);
    }

    /**
     */
    public dk.anaha.easykv.server.communication.Easykv.Bool unwatch(dk.anaha.easykv.server.communication.Easykv.KeyWatcher request) {
      return blockingUnaryCall(
          getChannel(), getUnwatchMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class easykvFutureStub extends io.grpc.stub.AbstractFutureStub<easykvFutureStub> {
    private easykvFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected easykvFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new easykvFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> register(
        dk.anaha.easykv.server.communication.Easykv.ClientId request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Value> read(
        dk.anaha.easykv.server.communication.Easykv.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getReadMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> write(
        dk.anaha.easykv.server.communication.Easykv.KeyValue request) {
      return futureUnaryCall(
          getChannel().newCall(getWriteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> delete(
        dk.anaha.easykv.server.communication.Easykv.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> exist(
        dk.anaha.easykv.server.communication.Easykv.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getExistMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Keys> list(
        dk.anaha.easykv.server.communication.Easykv.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> lpush(
        dk.anaha.easykv.server.communication.Easykv.KeyString request) {
      return futureUnaryCall(
          getChannel().newCall(getLpushMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> rpush(
        dk.anaha.easykv.server.communication.Easykv.KeyString request) {
      return futureUnaryCall(
          getChannel().newCall(getRpushMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.StringValue> lpop(
        dk.anaha.easykv.server.communication.Easykv.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getLpopMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.StringValue> rpop(
        dk.anaha.easykv.server.communication.Easykv.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getRpopMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> ltrim(
        dk.anaha.easykv.server.communication.Easykv.KeyTrim request) {
      return futureUnaryCall(
          getChannel().newCall(getLtrimMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.IntValue> llen(
        dk.anaha.easykv.server.communication.Easykv.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getLlenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> lcontains(
        dk.anaha.easykv.server.communication.Easykv.KeyString request) {
      return futureUnaryCall(
          getChannel().newCall(getLcontainsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Strings> lget(
        dk.anaha.easykv.server.communication.Easykv.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getLgetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.LongValue> incrby(
        dk.anaha.easykv.server.communication.Easykv.KeyLong request) {
      return futureUnaryCall(
          getChannel().newCall(getIncrbyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.LongValue> decrby(
        dk.anaha.easykv.server.communication.Easykv.KeyLong request) {
      return futureUnaryCall(
          getChannel().newCall(getDecrbyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> expire(
        dk.anaha.easykv.server.communication.Easykv.KeyLong request) {
      return futureUnaryCall(
          getChannel().newCall(getExpireMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> expireat(
        dk.anaha.easykv.server.communication.Easykv.KeyLong request) {
      return futureUnaryCall(
          getChannel().newCall(getExpireatMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.LongValue> ttl(
        dk.anaha.easykv.server.communication.Easykv.Key request) {
      return futureUnaryCall(
          getChannel().newCall(getTtlMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> watch(
        dk.anaha.easykv.server.communication.Easykv.KeyWatcher request) {
      return futureUnaryCall(
          getChannel().newCall(getWatchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.anaha.easykv.server.communication.Easykv.Bool> unwatch(
        dk.anaha.easykv.server.communication.Easykv.KeyWatcher request) {
      return futureUnaryCall(
          getChannel().newCall(getUnwatchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_READ = 1;
  private static final int METHODID_WRITE = 2;
  private static final int METHODID_DELETE = 3;
  private static final int METHODID_EXIST = 4;
  private static final int METHODID_LIST = 5;
  private static final int METHODID_LPUSH = 6;
  private static final int METHODID_RPUSH = 7;
  private static final int METHODID_LPOP = 8;
  private static final int METHODID_RPOP = 9;
  private static final int METHODID_LTRIM = 10;
  private static final int METHODID_LLEN = 11;
  private static final int METHODID_LCONTAINS = 12;
  private static final int METHODID_LGET = 13;
  private static final int METHODID_INCRBY = 14;
  private static final int METHODID_DECRBY = 15;
  private static final int METHODID_EXPIRE = 16;
  private static final int METHODID_EXPIREAT = 17;
  private static final int METHODID_TTL = 18;
  private static final int METHODID_WATCH = 19;
  private static final int METHODID_UNWATCH = 20;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final easykvImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(easykvImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((dk.anaha.easykv.server.communication.Easykv.ClientId) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
          break;
        case METHODID_READ:
          serviceImpl.read((dk.anaha.easykv.server.communication.Easykv.Key) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Value>) responseObserver);
          break;
        case METHODID_WRITE:
          serviceImpl.write((dk.anaha.easykv.server.communication.Easykv.KeyValue) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((dk.anaha.easykv.server.communication.Easykv.Key) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
          break;
        case METHODID_EXIST:
          serviceImpl.exist((dk.anaha.easykv.server.communication.Easykv.Key) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
          break;
        case METHODID_LIST:
          serviceImpl.list((dk.anaha.easykv.server.communication.Easykv.Key) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Keys>) responseObserver);
          break;
        case METHODID_LPUSH:
          serviceImpl.lpush((dk.anaha.easykv.server.communication.Easykv.KeyString) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
          break;
        case METHODID_RPUSH:
          serviceImpl.rpush((dk.anaha.easykv.server.communication.Easykv.KeyString) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
          break;
        case METHODID_LPOP:
          serviceImpl.lpop((dk.anaha.easykv.server.communication.Easykv.Key) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.StringValue>) responseObserver);
          break;
        case METHODID_RPOP:
          serviceImpl.rpop((dk.anaha.easykv.server.communication.Easykv.Key) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.StringValue>) responseObserver);
          break;
        case METHODID_LTRIM:
          serviceImpl.ltrim((dk.anaha.easykv.server.communication.Easykv.KeyTrim) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
          break;
        case METHODID_LLEN:
          serviceImpl.llen((dk.anaha.easykv.server.communication.Easykv.Key) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.IntValue>) responseObserver);
          break;
        case METHODID_LCONTAINS:
          serviceImpl.lcontains((dk.anaha.easykv.server.communication.Easykv.KeyString) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
          break;
        case METHODID_LGET:
          serviceImpl.lget((dk.anaha.easykv.server.communication.Easykv.Key) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Strings>) responseObserver);
          break;
        case METHODID_INCRBY:
          serviceImpl.incrby((dk.anaha.easykv.server.communication.Easykv.KeyLong) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue>) responseObserver);
          break;
        case METHODID_DECRBY:
          serviceImpl.decrby((dk.anaha.easykv.server.communication.Easykv.KeyLong) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue>) responseObserver);
          break;
        case METHODID_EXPIRE:
          serviceImpl.expire((dk.anaha.easykv.server.communication.Easykv.KeyLong) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
          break;
        case METHODID_EXPIREAT:
          serviceImpl.expireat((dk.anaha.easykv.server.communication.Easykv.KeyLong) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
          break;
        case METHODID_TTL:
          serviceImpl.ttl((dk.anaha.easykv.server.communication.Easykv.Key) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.LongValue>) responseObserver);
          break;
        case METHODID_WATCH:
          serviceImpl.watch((dk.anaha.easykv.server.communication.Easykv.KeyWatcher) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
          break;
        case METHODID_UNWATCH:
          serviceImpl.unwatch((dk.anaha.easykv.server.communication.Easykv.KeyWatcher) request,
              (io.grpc.stub.StreamObserver<dk.anaha.easykv.server.communication.Easykv.Bool>) responseObserver);
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

  private static abstract class easykvBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    easykvBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dk.anaha.easykv.server.communication.Easykv.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("easykv");
    }
  }

  private static final class easykvFileDescriptorSupplier
      extends easykvBaseDescriptorSupplier {
    easykvFileDescriptorSupplier() {}
  }

  private static final class easykvMethodDescriptorSupplier
      extends easykvBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    easykvMethodDescriptorSupplier(String methodName) {
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
      synchronized (easykvGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new easykvFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .addMethod(getReadMethod())
              .addMethod(getWriteMethod())
              .addMethod(getDeleteMethod())
              .addMethod(getExistMethod())
              .addMethod(getListMethod())
              .addMethod(getLpushMethod())
              .addMethod(getRpushMethod())
              .addMethod(getLpopMethod())
              .addMethod(getRpopMethod())
              .addMethod(getLtrimMethod())
              .addMethod(getLlenMethod())
              .addMethod(getLcontainsMethod())
              .addMethod(getLgetMethod())
              .addMethod(getIncrbyMethod())
              .addMethod(getDecrbyMethod())
              .addMethod(getExpireMethod())
              .addMethod(getExpireatMethod())
              .addMethod(getTtlMethod())
              .addMethod(getWatchMethod())
              .addMethod(getUnwatchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
