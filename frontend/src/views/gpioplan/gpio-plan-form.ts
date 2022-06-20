import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-form-layout/vaadin-form-layout.js';
import '@vaadin/form-layout/src/vaadin-form-item.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import '@vaadin/integer-field/src/vaadin-integer-field.js';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/checkbox/src/vaadin-checkbox.js';

@customElement('gpio-plan-form')
export class GpioPlanForm extends LitElement {
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
<vaadin-vertical-layout theme="spacing" style="width: 100%; height: 100%;">
 <vaadin-horizontal-layout style="justify-content: center; flex-direction: row; width: 100%;" id="typeLayout"></vaadin-horizontal-layout>
 <vaadin-form-layout>
  <vaadin-text-field id="name" type="text" required tabindex="" label="Name" invalid></vaadin-text-field>
  <vaadin-combo-box id="event" required tabindex="" prevent-invalid-input label="Event"></vaadin-combo-box>
  <vaadin-combo-box id="address" tabindex="" label="Gpio address"></vaadin-combo-box>
  <vaadin-combo-box tabindex="" label="Deafault voltage" id="defaultState"></vaadin-combo-box>
  <vaadin-integer-field id="priority" has-controls type="number" required min="0" placeholder="0-100" value="50" max="100" tabindex="" label="Priority" name="priority" step="5"></vaadin-integer-field>
 </vaadin-form-layout>
 <vaadin-form-layout>
  <vaadin-text-field label="Time duration" id="timeDuration" type="text" tabindex=""></vaadin-text-field>
  <vaadin-text-field label="Last triggered" id="lastTriggered" type="text" tabindex=""></vaadin-text-field>
  <vaadin-checkbox id="turnedOn" tabindex="" type="checkbox" value="on">
    Is on 
  </vaadin-checkbox>
 </vaadin-form-layout>
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
