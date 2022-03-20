import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/button/src/vaadin-button.js';

@customElement('sensor-form')
export class SensorForm extends LitElement {
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
 <vaadin-text-field label="Sensor ID" id="id" type="text" readonly tabindex=""></vaadin-text-field>
 <vaadin-text-field label="Name" id="name" type="text" required tabindex=""></vaadin-text-field>
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

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
