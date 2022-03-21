import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/grid/src/vaadin-grid.js';
import './action-config-form';

@customElement('action-config-view')
export class ActionConfigView extends LitElement {
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
  <vaadin-text-field placeholder="Filter by name…" type="text" tabindex="" clear-button-visible id="filterText"></vaadin-text-field>
  <vaadin-button id="addActionButton">
   Add Action
  </vaadin-button>
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout theme="spacing" style="height: 100%; width: 100%;">
  <vaadin-grid style="width: 100%; height: 100%;flex: 2;" tabindex="" is-attached id="grid"></vaadin-grid>
  <action-config-form style="flex:1;" id="contactForm"></action-config-form>
 </vaadin-horizontal-layout>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
