import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/board/src/vaadin-board.js';
import '@vaadin/board/src/vaadin-board-row.js';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';

@customElement('settings-view')
export class SettingsView extends LitElement {
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
<vaadin-vertical-layout style="align-items: flex-start;">
 <h2 style="align-self: center;">Sensors viewable in dashboard</h2>
 <vaadin-board style="width: 100%; align-self: flex-end;">
  <vaadin-board-row>
   <vaadin-vertical-layout theme="spacing" style="flex: 1; min-height: 400px; max-width: 350px; margin: var(--lumo-space-m);">
    <h4 style="align-self: center;">Temperature sensors</h4>
    <vaadin-grid id="temperatureSensorGrid" style="flex: 1; " tabindex="" is-attached></vaadin-grid>
   </vaadin-vertical-layout>
   <vaadin-vertical-layout theme="spacing" style="flex: 1; min-height: 400px; max-width: 350px; margin: var(--lumo-space-m);">
    <h4 style="align-self: center;">Humidity sensors</h4>
    <vaadin-grid id="humiditySensorGrid" style="flex: 1; " tabindex="" is-attached></vaadin-grid>
   </vaadin-vertical-layout>
   <vaadin-vertical-layout theme="spacing" style="flex: 1; min-height: 400px; max-width: 350px; margin: var(--lumo-space-m);">
    <h4 style="align-self: center;">Co2 sensors</h4>
    <vaadin-grid id="co2SensorGrid" style="flex: 1;" tabindex="" is-attached></vaadin-grid>
   </vaadin-vertical-layout>
  </vaadin-board-row>
 </vaadin-board>
 <vaadin-button id="saveSensors" style="margin: var(--lumo-space-m);">
   Save 
 </vaadin-button>
 <h2 style="align-self: center;">Device datasource address</h2>
 <vaadin-form-layout style="width: 100%; height: 100%;">
  <vaadin-text-field type="text" tabindex="" label="place"></vaadin-text-field>
  <vaadin-text-field type="text" placeholder="place" tabindex="" label="place"></vaadin-text-field>
  <vaadin-text-field type="text" tabindex="" label="place"></vaadin-text-field>
 </vaadin-form-layout>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
