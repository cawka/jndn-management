/*
 * jndn-management
 * Copyright (c) 2015, Intel Corporation.
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms and conditions of the GNU Lesser General Public License,
 * version 3, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 */
package com.intel.jndn.management.types;

import com.intel.jndn.management.TestHelper;
import com.intel.jndn.management.enums.FacePersistency;
import com.intel.jndn.management.enums.FaceScope;
import com.intel.jndn.management.enums.LinkType;
import net.named_data.jndn.util.Blob;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test encode/decode of FaceStatus.
 *
 * @author Andrew Brown <andrew.brown@intel.com>
 */
public class FaceStatusTest {
  private ByteBuffer testFaceStatusWire;

  @Before
  public void setUp() throws Exception {
    testFaceStatusWire = TestHelper.bufferFromIntArray(new int[] {
      0x80, 0x6a, 0x69, 0x01, 0x64, 0x72, 0x15, 0x74, 0x63, 0x70,
      0x34, 0x3a, 0x2f, 0x2f, 0x31, 0x39, 0x32, 0x2e, 0x30, 0x2e,
      0x32, 0x2e, 0x31, 0x3a, 0x36, 0x33, 0x36, 0x33, 0x81, 0x16,
      0x74, 0x63, 0x70, 0x34, 0x3a, 0x2f, 0x2f, 0x31, 0x39, 0x32,
      0x2e, 0x30, 0x2e, 0x32, 0x2e, 0x32, 0x3a, 0x35, 0x35, 0x35,
      0x35, 0x35, 0x6d, 0x02, 0x27, 0x10, 0x84, 0x01, 0x01, 0x85,
      0x01, 0x01, 0x86, 0x01, 0x01, 0x87, 0x01, 0x05, 0x88, 0x01,
      0x07, 0x89, 0x01, 0x09, 0x90, 0x01, 0x0a, 0x91, 0x01, 0xc8,
      0x97, 0x01, 0x01, 0x92, 0x02, 0x0b, 0xb8, 0x93, 0x01, 0x04,
      0x98, 0x01, 0x02, 0x94, 0x04, 0x4f, 0x41, 0xe7, 0x7b, 0x95,
      0x04, 0x3b, 0x8d, 0x37, 0x30, 0x6c, 0x01, 0x07,
    });
  }

  @Test
  public void testSettersGetters() throws Exception {
    FaceStatus status = new FaceStatus();
    status
      .setFaceId(100)
      .setRemoteUri("tcp4://192.0.2.1:6363")
      .setLocalUri("tcp4://192.0.2.2:55555")
      .setFaceScope(FaceScope.LOCAL)
      .setFacePersistency(FacePersistency.ON_DEMAND)
      .setLinkType(LinkType.MULTI_ACCESS)
      .setExpirationPeriod(10000)
      .setBaseCongestionMarkingInterval(5)
      .setDefaultCongestionThreshold(7)
      .setMtu(9)
      .setNInInterests(10)
      .setNInData(200)
      .setNInNacks(1)
      .setNOutInterests(3000)
      .setNOutData(4)
      .setNOutNacks(2)
      .setNInBytes(1329719163)
      .setNOutBytes(999110448)
      .setFlags(0x7);

    assertEquals(100, status.getFaceId());
    assertEquals("tcp4://192.0.2.1:6363", status.getRemoteUri());
    assertEquals("tcp4://192.0.2.2:55555", status.getLocalUri());
    assertEquals(FaceScope.LOCAL, status.getFaceScope());
    assertEquals(FacePersistency.ON_DEMAND, status.getFacePersistency());
    assertEquals(LinkType.MULTI_ACCESS, status.getLinkType());
    assertEquals(10000, status.getExpirationPeriod());
    assertEquals(10, status.getNInInterests());
    assertEquals(200, status.getNInData());
    assertEquals(1, status.getNInNacks());
    assertEquals(3000, status.getNOutInterests());
    assertEquals(4, status.getNOutData());
    assertEquals(2, status.getNOutNacks());
    assertEquals(1329719163, status.getNInBytes());
    assertEquals(999110448, status.getNOutBytes());

    assertEquals(9, status.getMtu());
    assertEquals(0x7, status.getFlags());
    assertEquals(5, status.getBaseCongestionMarkingInterval());
    assertEquals(7, status.getDefaultCongestionThreshold());
    assertTrue(status.hasMtu());
    assertTrue(status.hasBaseCongestionMarkingInterval());
    assertTrue(status.hasDefaultCongestionThreshold());
  }

  @Test
  public void testEncode() throws Exception {
    FaceStatus status = new FaceStatus();
    status
      .setFaceId(100)
      .setRemoteUri("tcp4://192.0.2.1:6363")
      .setLocalUri("tcp4://192.0.2.2:55555")
      .setFaceScope(FaceScope.LOCAL)
      .setFacePersistency(FacePersistency.ON_DEMAND)
      .setLinkType(LinkType.MULTI_ACCESS)
      .setExpirationPeriod(10000)
      .setBaseCongestionMarkingInterval(5)
      .setDefaultCongestionThreshold(7)
      .setMtu(9)
      .setNInInterests(10)
      .setNInData(200)
      .setNInNacks(1)
      .setNOutInterests(3000)
      .setNOutData(4)
      .setNOutNacks(2)
      .setNInBytes(1329719163)
      .setNOutBytes(999110448)
      .setFlags(0x7);

    // encode
    Blob encoded = status.wireEncode();
//    for (final byte b : encoded.getImmutableArray()) {
//      System.err.format("0x%02x, ", b);
//    }
    assertEquals(testFaceStatusWire, encoded.buf());
  }

  @Test
  public void testDecode() throws Exception {
    FaceStatus status = new FaceStatus(testFaceStatusWire);

    assertEquals(100, status.getFaceId());
    assertEquals("tcp4://192.0.2.1:6363", status.getRemoteUri());
    assertEquals("tcp4://192.0.2.2:55555", status.getLocalUri());
    assertEquals(FaceScope.LOCAL, status.getFaceScope());
    assertEquals(FacePersistency.ON_DEMAND, status.getFacePersistency());
    assertEquals(LinkType.MULTI_ACCESS, status.getLinkType());
    assertEquals(10000, status.getExpirationPeriod());
    assertEquals(10, status.getNInInterests());
    assertEquals(200, status.getNInData());
    assertEquals(1, status.getNInNacks());
    assertEquals(3000, status.getNOutInterests());
    assertEquals(4, status.getNOutData());
    assertEquals(2, status.getNOutNacks());
    assertEquals(1329719163, status.getNInBytes());
    assertEquals(999110448, status.getNOutBytes());

    assertEquals(9, status.getMtu());
    assertEquals(0x7, status.getFlags());
    assertEquals(5, status.getBaseCongestionMarkingInterval());
    assertEquals(7, status.getDefaultCongestionThreshold());
    assertTrue(status.hasMtu());
    assertTrue(status.hasBaseCongestionMarkingInterval());
    assertTrue(status.hasDefaultCongestionThreshold());
  }

  @Test
  public void testToString() throws Exception {
    FaceStatus status = new FaceStatus(testFaceStatusWire);

    assertEquals(
      "Face(FaceId: 100,\n" +
      "     RemoteUri: tcp4://192.0.2.1:6363,\n" +
      "     LocalUri: tcp4://192.0.2.2:55555,\n" +
      "     ExpirationPeriod: 10000 milliseconds,\n" +
      "     FaceScope: local,\n" +
      "     FacePersistency: on-demand,\n" +
      "     LinkType: multi-access,\n" +
      "     BaseCongestionMarkingInterval: 5 nanoseconds,\n" +
      "     DefaultCongestionThreshold: 7 bytes,\n" +
      "     Mtu: 9 bytes,\n" +
      "     Flags: 0x7,\n" +
      "     Counters: {Interests: {in: 10, out: 3000},\n" +
      "                Data: {in: 200, out: 4},\n" +
      "                Nacks: {in: 1, out: 2},\n" +
      "                bytes: {in: 1329719163, out: 999110448}}\n" +
      "     )",
      status.toString());

    status.setExpirationPeriod(0);
    assertEquals(
      "Face(FaceId: 100,\n" +
      "     RemoteUri: tcp4://192.0.2.1:6363,\n" +
        "     LocalUri: tcp4://192.0.2.2:55555,\n" +
        "     ExpirationPeriod: infinite,\n" +
        "     FaceScope: local,\n" +
        "     FacePersistency: on-demand,\n" +
        "     LinkType: multi-access,\n" +
        "     BaseCongestionMarkingInterval: 5 nanoseconds,\n" +
        "     DefaultCongestionThreshold: 7 bytes,\n" +
        "     Mtu: 9 bytes,\n" +
        "     Flags: 0x7,\n" +
        "     Counters: {Interests: {in: 10, out: 3000},\n" +
        "                Data: {in: 200, out: 4},\n" +
        "                Nacks: {in: 1, out: 2},\n" +
        "                bytes: {in: 1329719163, out: 999110448}}\n" +
        "     )",
      status.toString());
  }
}
