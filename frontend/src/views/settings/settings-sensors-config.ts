import { LitElement, html, css, customElement } from 'lit-element';

@customElement('settings-sensors-config')
export class SettingsSensorsConfig extends LitElement {
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
 <h2 style="align-self: center; margin: var(--lumo-space-m);">Sensors viewable in dashboard</h2>
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
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
