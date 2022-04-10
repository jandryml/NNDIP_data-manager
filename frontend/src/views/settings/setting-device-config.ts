import {css, customElement, html, LitElement} from 'lit-element';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/board/src/vaadin-board.js';
import '@vaadin/board/src/vaadin-board-row.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';

@customElement('setting-device-config')
export class SettingDeviceConfig extends LitElement {
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
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <h2 style="align-self: center;">Device datasource address</h2>
 <vaadin-form-layout style="margin: var(--lumo-space-m);">
  <vaadin-board style="width: 100%; min-width: 100%;">
   <h4 style="margin: var(--lumo-space-m);">AC - ON/OFF</h4>
   <vaadin-board-row style="width: 100%;">
    <vaadin-text-field style="margin-right: var(--lumo-space-m); margin-left: var(--lumo-space-m); flex: 1; max-width: 250px;" type="text" tabindex="" label="Address" id="acStatusAddress"></vaadin-text-field>
    <vaadin-combo-box style="margin-right: var(--lumo-space-m); margin-left: var(--lumo-space-m); flex: 1; max-width: 250px;" tabindex="" label="Type" id="acStatusType"></vaadin-combo-box>
   </vaadin-board-row>
   <h4 style="margin: var(--lumo-space-m);">AC - Mode</h4>
   <vaadin-board-row style="width: 100%;">
    <vaadin-text-field style="margin-right: var(--lumo-space-m); margin-left: var(--lumo-space-m); flex: 1; max-width: 250px;" type="text" tabindex="" label="Address" id="acModeAddress"></vaadin-text-field>
    <vaadin-combo-box style="margin-right: var(--lumo-space-m); margin-left: var(--lumo-space-m); flex: 1; max-width: 250px;" tabindex="" label="Type" id="acModeType"></vaadin-combo-box>
   </vaadin-board-row>
   <h4 style="margin: var(--lumo-space-m);">AC - Fan</h4>
   <vaadin-board-row style="width: 100%;">
    <vaadin-text-field style="margin-right: var(--lumo-space-m); margin-left: var(--lumo-space-m); flex: 1; max-width: 250px;" type="text" tabindex="" label="Address" id="acFanAddress"></vaadin-text-field>
    <vaadin-combo-box style="margin-right: var(--lumo-space-m); margin-left: var(--lumo-space-m); flex: 1; max-width: 250px;" tabindex="" label="Type" id="acFanType"></vaadin-combo-box>
   </vaadin-board-row>
   <h4 style="margin: var(--lumo-space-m);">Recuperation</h4>
   <vaadin-board-row style="width: 100%;">
    <vaadin-text-field style="margin-right: var(--lumo-space-m); margin-left: var(--lumo-space-m); flex: 1; max-width: 250px;" type="text" tabindex="" label="Address" id="recuperationAddress"></vaadin-text-field>
    <vaadin-combo-box style="margin-right: var(--lumo-space-m); margin-left: var(--lumo-space-m); flex: 1; max-width: 250px;" tabindex="" label="Type" id="recuperationType"></vaadin-combo-box>
   </vaadin-board-row>
  </vaadin-board>
 </vaadin-form-layout>
 <vaadin-button id="saveDeviceConfig" style="margin: var(--lumo-space-m);">
   Save 
 </vaadin-button>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
