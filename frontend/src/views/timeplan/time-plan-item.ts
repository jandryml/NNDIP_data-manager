import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-form-layout/vaadin-form-layout.js';
import '@vaadin/form-layout/src/vaadin-form-item.js';
import '@vaadin/time-picker/src/vaadin-time-picker.js';
import '@vaadin/vaadin-form-layout/vaadin-form-item.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/checkbox/src/vaadin-checkbox.js';

@customElement('time-plan-item')
export class TimePlanItem extends LitElement {
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
 <vaadin-form-item>
  <label slot="label">From time</label>
  <vaadin-time-picker></vaadin-time-picker>
 </vaadin-form-item>
 <vaadin-form-item>
  <label slot="label">To time </label>
  <vaadin-time-picker tabindex=""></vaadin-time-picker>
 </vaadin-form-item>
 <vaadin-form-item>
  <label slot="label">Action</label>
  <vaadin-combo-box></vaadin-combo-box>
 </vaadin-form-item>
 <vaadin-form-item>
  <label slot="label">Enabled</label>
  <vaadin-horizontal-layout theme="spacing-xl">
   <vaadin-checkbox tabindex="" type="checkbox" value="on"></vaadin-checkbox>
   <vaadin-button>
     Remove 
   </vaadin-button>
  </vaadin-horizontal-layout>
 </vaadin-form-item>
</vaadin-form-layout>
`;
  }
}
