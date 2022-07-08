import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import '@vaadin/date-picker/src/vaadin-date-picker.js';
import '@vaadin/charts/src/vaadin-chart.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/button/src/vaadin-button.js';
import './dashboard-graph';

@customElement('dashboard-view')
export class DashboardView extends LitElement {
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
 <vaadin-vertical-layout style="width: 100%;">
  <h3 style="align-self: center; margin-top: var(--lumo-space-m);">Sensor status</h3>
 </vaadin-vertical-layout>
 <vaadin-text-field label="Active time plan" placeholder="Placeholder" id="activeTimePlan" style="align-self: center;" type="text" readonly tabindex=""></vaadin-text-field>
 <vaadin-form-layout style="align-self: center; margin: var(--lumo-space-m); flex-shrink: 0; flex-grow: 0;">
  <vaadin-vertical-layout style="flex: 1; padding-right: var(--lumo-space-l); padding-bottom: var(--lumo-space-l); padding-left: var(--lumo-space-l); flex-direction: column;" id="temperatureContainer">
   <h5 style="width: 100%; align-self: center; flex-shrink: 0;">Temperature </h5>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout style="flex: 1; padding-right: var(--lumo-space-l); padding-bottom: var(--lumo-space-l); padding-left: var(--lumo-space-l); flex-direction: column;" id="humidityContainer">
   <h5 style="align-self: center; width: 100%; flex-grow: 0; flex-shrink: 0;">Humidity </h5>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout style="flex: 1; padding-right: var(--lumo-space-l); padding-bottom: var(--lumo-space-l); padding-left: var(--lumo-space-l); flex-direction: column;" id="co2Container">
   <h5 style="align-self: center; width: 100%; flex-shrink: 0;">Co2</h5>
  </vaadin-vertical-layout>
 </vaadin-form-layout>
 <h3 style="align-self: center; margin-top: var(--lumo-space-m);">Device status</h3>
 <vaadin-form-layout style="align-self: center; margin: var(--lumo-space-m); flex-shrink: 0;">
  <vaadin-text-field label="Air Conditioning" placeholder="Unavailable" style="flex: 1; padding-right: var(--lumo-space-l); padding-left: var(--lumo-space-l); padding-bottom: var(--lumo-space-l);" type="text" readonly tabindex="" id="acStatus"></vaadin-text-field>
  <vaadin-text-field label="Recuperation" style="flex: 1; padding-right: var(--lumo-space-l); padding-left: var(--lumo-space-l); padding-bottom: var(--lumo-space-l);" type="text" readonly tabindex="" id="recuperationStatus"></vaadin-text-field>
  <vaadin-text-field label="Ventilator" style="flex: 1; padding-right: var(--lumo-space-l); padding-left: var(--lumo-space-l); padding-bottom: var(--lumo-space-l);" type="text" readonly tabindex="" id="ventStatus"></vaadin-text-field>
 </vaadin-form-layout>
 <dashboard-graph style="width: 100%;" id="graphComponent"></dashboard-graph>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
