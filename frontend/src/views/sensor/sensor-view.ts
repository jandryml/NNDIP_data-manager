import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/grid/src/vaadin-grid.js';
import './sensor-form';

@customElement('sensor-view')
export class SensorView extends LitElement {
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
<vaadin-vertical-layout theme="spacing" style="width: 100%; height: 100%; padding: var(--lumo-space-m);">
 <vaadin-horizontal-layout theme="spacing">
  <vaadin-text-field placeholder="Filter by name…" id="filterText" type="text" tabindex="" clear-button-visible></vaadin-text-field>
  <vaadin-button id="addSensorButton">
    Add Sensor 
  </vaadin-button>
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout theme="spacing" style="width: 100%; height: 100%;">
  <vaadin-grid id="grid" style="height: 100%;" tabindex="" is-attached></vaadin-grid>
  <sensor-form id="sensorForm" style="width: 100%; height: 100%;"></sensor-form>
 </vaadin-horizontal-layout>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
