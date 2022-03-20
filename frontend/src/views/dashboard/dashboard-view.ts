import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import '@vaadin/date-picker/src/vaadin-date-picker.js';
import '@vaadin/charts/src/vaadin-chart.js';

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
  <label style="align-self: center; margin-top: var(--lumo-space-m);">Actual status</label>
 </vaadin-vertical-layout>
 <vaadin-form-layout style="align-self: center; margin: var(--lumo-space-m);">
  <vaadin-text-field label="Temperature [°C]" placeholder="Unavailable" style="flex: 3; padding-right: var(--lumo-space-m);" type="text" readonly tabindex=""></vaadin-text-field>
  <vaadin-text-field label="Humidity [%]" style="flex: 3; padding-right: var(--lumo-space-m);" type="text" readonly tabindex=""></vaadin-text-field>
  <vaadin-text-field label="Co2 [ppm]" style="flex: 3; padding-right: var(--lumo-space-m);" type="text" readonly tabindex=""></vaadin-text-field>
 </vaadin-form-layout>
 <vaadin-form-layout style="align-self: center; margin: var(--lumo-space-m);">
  <vaadin-text-field label="Air Conditioning" placeholder="Unavailable" style="flex: 3; padding-right: var(--lumo-space-m);" type="text" readonly tabindex=""></vaadin-text-field>
  <vaadin-text-field label="Recuperation" style="flex: 3; padding-right: var(--lumo-space-m);" type="text" readonly tabindex=""></vaadin-text-field>
  <vaadin-text-field label="Ventilator" style="flex: 3; padding-right: var(--lumo-space-m);" type="text" readonly tabindex=""></vaadin-text-field>
 </vaadin-form-layout>
 <vaadin-vertical-layout style="width: 100%;">
  <label style="align-self: center;">Historical data</label>
 </vaadin-vertical-layout>
 <vaadin-vertical-layout theme="spacing" style="width: 100%;">
  <vaadin-chart title="Temperature today" categories="[&quot;00:00&quot;, &quot;01:00&quot;, &quot;02:00&quot;, &quot;03:00&quot;, &quot;04:00&quot;, &quot;05:00&quot;, &quot;06:00&quot;, &quot;07:00&quot;, &quot;08:00&quot;, &quot;09:00&quot;, &quot;10:00&quot;, &quot;11:00&quot;, &quot;12:00&quot;, &quot;13:00&quot;, &quot;14:00&quot;, &quot;15:00&quot;, &quot;16:00&quot;, &quot;17:00&quot;, &quot;18:00&quot;, &quot;19:00&quot;, &quot;20:00&quot;, &quot;21:00&quot;, &quot;22:00&quot;, &quot;23:00&quot;, &quot;24:00&quot;]" tooltip no-legend>
   <vaadin-chart-series title="Temperature" unit="[°C]" values="[21, 22, 23, 24, 24,26, 26, 25, 21, 22, 23, 24, 25, 26, 23, 24, 23, 22, 23, 24, 25, 26, 22, 25]"></vaadin-chart-series>
  </vaadin-chart>
  <vaadin-form-layout style="margin: var(--lumo-space-s); align-self: center;">
   <vaadin-combo-box style="flex: 2; padding: var(--lumo-space-s);" value="[test, test]" tabindex="" label="Select meassured value:"></vaadin-combo-box>
   <vaadin-date-picker style="flex: 2; padding: var(--lumo-space-s);" tabindex="" label="Select day:"></vaadin-date-picker>
  </vaadin-form-layout>
 </vaadin-vertical-layout>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
