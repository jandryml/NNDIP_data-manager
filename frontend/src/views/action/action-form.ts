import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';

@customElement('action-form')
export class ActionForm extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      `;
  }

  render() {
    return html`
<vaadin-form-layout style="width: 100%; height: 100%;">
 <vaadin-text-field label="Name" type="text" tabindex="" id="name" required autocomplete="OFF"></vaadin-text-field>
 <vaadin-text-field label="Address" type="text" tabindex="" id="address" pattern="&quot;^\\d+$&quot;" required autocomplete="OFF"></vaadin-text-field>
 <vaadin-text-field label="Value" id="value" type="text" required autocomplete="OFF" tabindex=""></vaadin-text-field>
 <vaadin-combo-box tabindex="" label="Output type" id="outputType" required></vaadin-combo-box>
 <vaadin-horizontal-layout theme="spacing">
  <vaadin-button theme="primary" id="save">
    Save 
  </vaadin-button>
  <vaadin-button theme="error" id="delete">
    Delete 
  </vaadin-button>
  <vaadin-button theme="tertiary" id="close">
    Close 
  </vaadin-button>
 </vaadin-horizontal-layout>
</vaadin-form-layout>
`;
  }
}
