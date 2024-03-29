/*  
 * Copyright 2008 CoreMedia AG, Hamburg
 *
 * Licensed under the Apache License, Version 2.0 (the License); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an AS IS BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */

package com.coremedia.iso.boxes.odf;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoOutputStream;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.ContainerBox;

import java.io.IOException;

/**
 * A mutable DRM information box may appear in both DCF and PDCF. In the OMA DRM system,
 * <code>MutableDrmInformationBox</code> is used to include information editable by the
 * device, and thus is protected for integrity. A device MUST ignore the box when
 * calculating the DCF Hash.
 */
public class MutableDrmInformationBox extends ContainerBox {
  public static final String TYPE = "mdri";

  public MutableDrmInformationBox() {
    super(IsoFile.fourCCtoBytes(TYPE));
  }

  public String getDisplayName() {
    return "Mutable DRM Information Box";
  }

  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("MutableDrmInformationBox[");
    Box[] boxes = getBoxes();
    for (int i = 0; i < boxes.length; i++) {
      if (i > 0) {
        buffer.append(";");
      }
      buffer.append(boxes[i].toString());
    }
    buffer.append("]");
    return buffer.toString();
  }

  protected void getContent(IsoOutputStream os) throws IOException {
    os.stopHashCalculation();
    super.getContent(os);
    os.startHashCalculation();
  }
}
