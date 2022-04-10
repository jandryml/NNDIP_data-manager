import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';

@customElement('dashboard-graph')
export class DashboardGraph extends LitElement {
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
  <h3 style="align-self: center;">Historical data</h3>
 </vaadin-vertical-layout>
 <vaadin-vertical-layout theme="spacing" style="width: 100%;">
  <vaadin-chart id="graphComponent"></vaadin-chart>
  <vaadin-horizontal-layout style="align-items: flex-end; flex-wrap: wrap; align-self: center;" theme="spacing">
   <vaadin-combo-box style="flex: 1;" tabindex="" label="Select sensor:" id="sensorComboBox"></vaadin-combo-box>
   <vaadin-combo-box style="flex: 1;" tabindex="" label="Select meassured value:" id="measuredValueComboBox"></vaadin-combo-box>
   <vaadin-date-picker style="flex: 1;" tabindex="" label="Select day:" id="selectedDate"></vaadin-date-picker>
   <vaadin-button theme="icon" aria-label="Decrement date" id="decrementDate">
    <iron-icon icon="lumo:minus"></iron-icon>
   </vaadin-button>
   <vaadin-button theme="icon" aria-label="Increment date" id="incrementDate">
    <iron-icon icon="lumo:plus"></iron-icon>
   </vaadin-button>
  </vaadin-horizontal-layout>
 </vaadin-vertical-layout>
</vaadin-vertical-layout>
`;
  }
}
