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
<vaadin-vertical-layout theme="spacing" style="width: 100%; height: 100%; margin: var(--lumo-space-m);">
 <vaadin-form-layout style="padding: var(--lumo-space-m);">
  <vaadin-text-field id="name" type="text" required tabindex="" label="Name"></vaadin-text-field>
  <vaadin-checkbox tabindex="" checked label="Enabled" type="checkbox" value="on" id="enabled"></vaadin-checkbox>
  <vaadin-time-picker id="fromTime" tabindex="" label="From time"></vaadin-time-picker>
  <vaadin-time-picker id="toTime" tabindex="" label="To time"></vaadin-time-picker>
 </vaadin-form-layout>
 <dynamic-action-component id="dynamicActionComponent" style="width: 80%;"></dynamic-action-component>
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
</vaadin-vertical-layout>
`;
  }
}
