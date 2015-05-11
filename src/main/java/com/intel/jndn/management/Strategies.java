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
package com.intel.jndn.management;

import net.named_data.jndn.Name;

/**
 * A reference list of the strategies available in NFD; should match
 * <a href="http://redmine.named-data.net/projects/nfd/wiki/StrategyChoice#Strategy">
 * http://redmine.named-data.net/projects/nfd/wiki/StrategyChoice#Strategy</a>
 *
 * @author Andrew Brown <andrew.brown@intel.com>
 */
public class Strategies {
  public static final Name BEST_ROUTE = new Name("/localhost/nfd/strategy/best-route");
  public static final Name BROADCAST = new Name("/localhost/nfd/strategy/broadcast");
  public static final Name CLIENT_CONTROL = new Name("/localhost/nfd/strategy/client-control");
  public static final Name NCC = new Name("/localhost/nfd/strategy/ncc");
}
