import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-form-layout/vaadin-form-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/form-layout/src/vaadin-form-item.js';
import '@vaadin/time-picker/src/vaadin-time-picker.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import '@vaadin/checkbox/src/vaadin-checkbox.js';

@customElement('time-plan-form')
export class TimePlanForm extends LitElement {
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
<vaadin-form-layout>
 <vaadin-time-picker tabindex="" label="From time"></vaadin-time-picker>
 <vaadin-time-picker tabindex="" label="To time"></vaadin-time-picker>
 <vaadin-combo-box tabindex="" label="Action 1"></vaadin-combo-box>
 <vaadin-combo-box tabindex="" label="Action 2"></vaadin-combo-box>
 <vaadin-checkbox tabindex="" type="checkbox" value="on" label="Enabled"></vaadin-checkbox>
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
