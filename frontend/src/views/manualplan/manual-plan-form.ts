import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-form-layout/vaadin-form-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import '@vaadin/number-field/src/vaadin-number-field.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/integer-field/src/vaadin-integer-field.js';

@customElement('manual-plan-form')
export class ManualPlanForm extends LitElement {
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
 <vaadin-form-layout>
  <vaadin-text-field id="name" type="text" required tabindex="" label="Name" invalid></vaadin-text-field>
  <vaadin-combo-box id="event" required tabindex="" prevent-invalid-input label="Event" invalid></vaadin-combo-box>
  <vaadin-integer-field id="priority" has-controls type="number" required min="0" placeholder="0-100" value="100" max="100" tabindex="" label="Priority" name="priority" step="5"></vaadin-integer-field>
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
