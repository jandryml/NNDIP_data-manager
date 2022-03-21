import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/button/src/vaadin-button.js';
import '@polymer/paper-toggle-button/paper-toggle-button.js';

@customElement('limit-config-view')
export class LimitConfigView extends LitElement {
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
 <vaadin-vertical-layout theme="spacing" style="flex: 3;">
  <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
   <label>Temperature config</label>
  </vaadin-horizontal-layout>
  <paper-toggle-button style="align-self: center;" active></paper-toggle-button>
  <vaadin-text-field label="Threshold" style="align-self: center;" type="text" tabindex=""></vaadin-text-field>
  <vaadin-text-field label="Tolerance" style="align-self: center;" type="text" tabindex=""></vaadin-text-field>
  <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
   <label>Action </label>
  </vaadin-horizontal-layout>
  <vaadin-combo-box style="align-self: center;" tabindex=""></vaadin-combo-box>
  <vaadin-button style="align-self: center;">
    Add action 
  </vaadin-button>
 </vaadin-vertical-layout>
 <vaadin-vertical-layout theme="spacing" style="flex: 3;">
  <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
   <label>Humidity config</label>
  </vaadin-horizontal-layout>
  <paper-toggle-button style="align-self: center;" pressed></paper-toggle-button>
  <vaadin-text-field label="Threshold" style="align-self: center;" type="text" tabindex=""></vaadin-text-field>
  <vaadin-text-field label="Tolerance" style="align-self: center;" type="text" tabindex=""></vaadin-text-field>
  <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
   <label>Action </label>
  </vaadin-horizontal-layout>
  <vaadin-combo-box style="align-self: center;" tabindex=""></vaadin-combo-box>
  <vaadin-button style="align-self: center;">
    Add action 
  </vaadin-button>
 </vaadin-vertical-layout>
 <vaadin-vertical-layout theme="spacing" style="flex: 3;">
  <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
   <label>Co2 config</label>
  </vaadin-horizontal-layout>
  <paper-toggle-button style="align-self: center;" active></paper-toggle-button>
  <vaadin-text-field label="Threshold" style="align-self: center;" type="text" tabindex=""></vaadin-text-field>
  <vaadin-text-field label="Tolerance" style="align-self: center;" type="text" tabindex=""></vaadin-text-field>
  <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
   <label>Action </label>
  </vaadin-horizontal-layout>
  <vaadin-combo-box style="align-self: center;" tabindex=""></vaadin-combo-box>
  <vaadin-button style="align-self: center;">
    Add action 
  </vaadin-button>
 </vaadin-vertical-layout>
</vaadin-form-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
