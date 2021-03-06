/*
 * Copyright (c) 2020 SeungyunLee
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of the copyright holders nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 */

package konkuk.sylee.assignment5.utils;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class UITool {
  public static String getSelectedButtonText(ButtonGroup buttonGroup) {
    for (Enumeration<AbstractButton> buttons = buttonGroup.getElements();
        buttons.hasMoreElements(); ) {
      AbstractButton button = buttons.nextElement();

      if (button.isSelected()) {
        return button.getText();
      }
    }
    return null;
  }
}
